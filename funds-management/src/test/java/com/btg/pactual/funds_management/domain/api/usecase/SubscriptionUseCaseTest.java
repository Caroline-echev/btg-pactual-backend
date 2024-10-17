package com.btg.pactual.funds_management.domain.api.usecase;

import com.btg.pactual.funds_management.data.FundData;
import com.btg.pactual.funds_management.data.TransactionData;
import com.btg.pactual.funds_management.data.UserData;
import com.btg.pactual.funds_management.domain.model.Transaction;
import com.btg.pactual.funds_management.domain.spi.*;
import com.btg.pactual.funds_management.domain.util.TransactionType;
import org.junit.jupiter.api.Test;

import static com.btg.pactual.funds_management.data.FundData.ID_FUND_A;
import static com.btg.pactual.funds_management.data.SubscriptionData.*;
import static com.btg.pactual.funds_management.data.SubscriptionData.INSUFFICIENT_AMOUNT;
import static com.btg.pactual.funds_management.data.UserData.USER_ID;
import static com.btg.pactual.funds_management.data.UserData.getUserWithUnsubscribe;
import static com.btg.pactual.funds_management.domain.util.DomainConstants.*;
import static org.junit.jupiter.api.Assertions.*;

import com.btg.pactual.funds_management.domain.exception.AlreadySubscribedException;
import com.btg.pactual.funds_management.domain.exception.FundNotFoundException;
import com.btg.pactual.funds_management.domain.exception.InsufficientBalanceException;
import com.btg.pactual.funds_management.domain.exception.UserNotFoundException;
import com.btg.pactual.funds_management.domain.model.Fund;
import com.btg.pactual.funds_management.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubscriptionUseCaseTest {


    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IFundPersistencePort fundPersistencePort;

    @Mock
    private ITransactionPersistencePort transactionPersistencePort;

    @Mock
    private  ISmsPersistencePort smsPersistencePort;

    @Mock
    private IEmailPersistencePort emailPersistencePort;

    @InjectMocks
    private SubscriptionUseCase subscriptionUseCase;

    private User user;
    private Fund fund;
    private Transaction transaction;
    private Transaction transactionUnsubscribe;
    private User userWithUnsubscribe;

    @BeforeEach
    public void setup() {
        user = UserData.getUser();
        fund = FundData.getFundA();
        transaction = TransactionData.getTransactionA();
        transactionUnsubscribe = TransactionData.getTransactionACancel();
        userWithUnsubscribe = UserData.getUserWithUnsubscribe();
    }



    @Test
    void testSubscribeToFundUserNotFound() {
        when(userPersistencePort.findById(USER_ID)).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> {
            subscriptionUseCase.subscribeToFund(USER_ID, ID_FUND_A, false, INSUFFICIENT_AMOUNT);
        });
    }
    @Test
    void testSubscribeToFundInsufficientAmount() {
        user.setInitialBalance(BigDecimal.valueOf(100));
        fund.setMinimumAmount(BigDecimal.valueOf(200));

        when(userPersistencePort.findById(USER_ID)).thenReturn(user);
        when(fundPersistencePort.findById(ID_FUND_A)).thenReturn(fund);

        assertThrows(InsufficientBalanceException.class, () -> {
            subscriptionUseCase.subscribeToFund(USER_ID, ID_FUND_A, false, BigDecimal.valueOf(150));
        });
    }
    @Test
    void testUnsubscribeToFundFundNotFound() {
        when(userPersistencePort.findById(USER_ID)).thenReturn(user);
        when(fundPersistencePort.findById(ID_FUND_A)).thenReturn(null);

        assertThrows(FundNotFoundException.class, () -> {
            subscriptionUseCase.unsubscribeToFund(USER_ID, ID_FUND_A, false);
        });
    }

    @Test
    void testUnsubscribeToFundSuccessful() {
        user.getSubscriptions().add(ID_FUND_A);
        user.setInitialBalance(BigDecimal.valueOf(800));
        transaction.setAmount(BigDecimal.valueOf(200));

        when(userPersistencePort.findById(USER_ID)).thenReturn(user);
        when(fundPersistencePort.findById(ID_FUND_A)).thenReturn(fund);
        when(transactionPersistencePort.findTransactionsByUserIdAndFundIdAndType(USER_ID, ID_FUND_A, TransactionType.SUBSCRIPTION.name()))
                .thenReturn(transaction);
        when(userPersistencePort.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);
        when(transactionPersistencePort.save(any(Transaction.class))).thenAnswer(i -> i.getArguments()[0]);

        subscriptionUseCase.unsubscribeToFund(USER_ID, ID_FUND_A, false);

        assertEquals(BigDecimal.valueOf(1000), user.getInitialBalance());

    }

    @Test
    void testSubscribeToFundSuccessful() {
        user.setInitialBalance(BigDecimal.valueOf(1000));
        fund.setMinimumAmount(BigDecimal.valueOf(100));

        when(userPersistencePort.findById(USER_ID)).thenReturn(user);
        when(fundPersistencePort.findById(ID_FUND_A)).thenReturn(fund);
        when(userPersistencePort.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);
        when(transactionPersistencePort.save(any(Transaction.class))).thenAnswer(i -> i.getArguments()[0]);

        subscriptionUseCase.subscribeToFund(USER_ID, ID_FUND_A, false, BigDecimal.valueOf(200));

        assertEquals(BigDecimal.valueOf(800), user.getInitialBalance());
        assertTrue(user.getSubscriptions().contains(ID_FUND_A));
    }
    @Test
    void testSubscribeToFundFundNotFound() {
        when(userPersistencePort.findById(USER_ID)).thenReturn(user);
        when(fundPersistencePort.findById(ID_FUND_A)).thenReturn(null);

        assertThrows(FundNotFoundException.class, () -> {
            subscriptionUseCase.subscribeToFund(USER_ID, ID_FUND_A, false, INSUFFICIENT_AMOUNT);
        });
    }


    @Test
    void testSubscribeToFundAlreadySubscribed() {
        user.getSubscriptions().add(ID_FUND_A);

        when(userPersistencePort.findById(USER_ID)).thenReturn(user);
        when(fundPersistencePort.findById(ID_FUND_A)).thenReturn(FundData.getFundA());

        assertThrows(AlreadySubscribedException.class, () -> {
            subscriptionUseCase.subscribeToFund(USER_ID, ID_FUND_A, false, INSUFFICIENT_AMOUNT);
        });
    }

    @Test
    void testUnsubscribeUserNotFound() {
        when(userPersistencePort.findById(USER_ID)).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> {
            subscriptionUseCase.unsubscribeToFund(USER_ID, ID_FUND_A, false);
        });
    }

    @Test
    void testSubscribeToFund_AlreadySubscribed() {

        User user = new User();
        user.setSubscriptions(List.of(ID_FUND_A));
        when(userPersistencePort.findById(USER_ID)).thenReturn(user);
        when(fundPersistencePort.findById(ID_FUND_A)).thenReturn(FundData.getFundA());

        assertThrows(AlreadySubscribedException.class, () -> {
            subscriptionUseCase.subscribeToFund(USER_ID, ID_FUND_A, true, BigDecimal.TEN);
        });
    }

    @Test
    void testSubscribeToFundMinimumAmountViolation() {
        when(userPersistencePort.findById(USER_ID)).thenReturn(user);
        when(fundPersistencePort.findById(ID_FUND_A)).thenReturn(fund);

        BigDecimal invalidAmount = fund.getMinimumAmount().subtract(BigDecimal.ONE);

        assertThrows(InsufficientBalanceException.class, () -> {
            subscriptionUseCase.subscribeToFund(USER_ID, ID_FUND_A, false, invalidAmount);
        });
    }

    @Test
    void testSubscribeToFundExceedsUserBalance() {
        when(userPersistencePort.findById(USER_ID)).thenReturn(user);
        when(fundPersistencePort.findById(ID_FUND_A)).thenReturn(fund);

        BigDecimal amountExceedsBalance = user.getInitialBalance().add(BigDecimal.ONE);

        assertThrows(InsufficientBalanceException.class, () -> {
            subscriptionUseCase.subscribeToFund(USER_ID, ID_FUND_A, false, amountExceedsBalance);
        });
    }


}
