package com.btg.pactual.funds_management.domain.api.usecase;

import com.btg.pactual.funds_management.data.FundData;
import com.btg.pactual.funds_management.data.UserData;
import com.btg.pactual.funds_management.domain.model.Transaction;
import org.junit.jupiter.api.Test;

import static com.btg.pactual.funds_management.data.FundData.ID_FUND_A;
import static com.btg.pactual.funds_management.data.SubscriptionData.*;
import static com.btg.pactual.funds_management.data.UserData.USER_ID;
import static org.junit.jupiter.api.Assertions.*;

import com.btg.pactual.funds_management.domain.exception.AlreadySubscribedException;
import com.btg.pactual.funds_management.domain.exception.FundNotFoundException;
import com.btg.pactual.funds_management.domain.exception.InsufficientBalanceException;
import com.btg.pactual.funds_management.domain.exception.UserNotFoundException;
import com.btg.pactual.funds_management.domain.model.Fund;
import com.btg.pactual.funds_management.domain.model.User;
import com.btg.pactual.funds_management.domain.spi.IFundPersistencePort;
import com.btg.pactual.funds_management.domain.spi.ITransactionPersistencePort;
import com.btg.pactual.funds_management.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubscriptionUseCaseTest {


    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IFundPersistencePort fundPersistencePort;

    @Mock
    private ITransactionPersistencePort transactionPersistencePort;

    @InjectMocks
    private SubscriptionUseCase subscriptionUseCase;

    private User user;
    private Fund fund;

    @BeforeEach
    public void setup() {
        user = UserData.getUser();
        fund = FundData.getFundA();
    }

    @Test
    void testSubscribeToFundSuccess() {
        when(userPersistencePort.findById(USER_ID)).thenReturn(user);
        when(fundPersistencePort.findById(ID_FUND_A)).thenReturn(fund);

        subscriptionUseCase.subscribeToFund(USER_ID, ID_FUND_A, false, SUBSCRIPTION_AMOUNT);

        assertEquals(HIGH_BALANCE, user.getInitialBalance());
        assertTrue(user.getSubscriptions().contains(ID_FUND_A));

        ArgumentCaptor<Transaction> transactionCaptor = ArgumentCaptor.forClass(Transaction.class);
        verify(transactionPersistencePort).save(transactionCaptor.capture());
        assertEquals(USER_ID, transactionCaptor.getValue().getUserId());
        assertEquals(ID_FUND_A, transactionCaptor.getValue().getFundId());
        assertEquals(SUBSCRIPTION_AMOUNT, transactionCaptor.getValue().getAmount());
    }

    @Test
    void testSubscribeToFundUserNotFound() {
        when(userPersistencePort.findById(USER_ID)).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> {
            subscriptionUseCase.subscribeToFund(USER_ID, ID_FUND_A, false, INSUFFICIENT_AMOUNT);
        });
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
    void testSubscribeToFundInsufficientBalance() {
        when(userPersistencePort.findById(USER_ID)).thenReturn(user);
        when(fundPersistencePort.findById(ID_FUND_A)).thenReturn(FundData.getFundA());

        assertThrows(InsufficientBalanceException.class, () -> {
            subscriptionUseCase.subscribeToFund(USER_ID, ID_FUND_A, false, EXCEEDING_BALANCE);
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
