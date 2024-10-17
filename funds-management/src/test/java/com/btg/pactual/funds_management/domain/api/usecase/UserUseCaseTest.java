package com.btg.pactual.funds_management.domain.api.usecase;

import com.btg.pactual.funds_management.data.FundData;
import com.btg.pactual.funds_management.data.TransactionData;
import com.btg.pactual.funds_management.data.UserData;
import com.btg.pactual.funds_management.domain.exception.UserNotFoundException;
import com.btg.pactual.funds_management.domain.model.Fund;
import com.btg.pactual.funds_management.domain.model.Subscription;
import com.btg.pactual.funds_management.domain.model.Transaction;
import com.btg.pactual.funds_management.domain.model.User;
import com.btg.pactual.funds_management.domain.spi.IFundPersistencePort;
import com.btg.pactual.funds_management.domain.spi.ITransactionPersistencePort;
import com.btg.pactual.funds_management.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static com.btg.pactual.funds_management.data.FundData.*;
import static com.btg.pactual.funds_management.data.UserData.USER_ID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserUseCaseTest {

    @InjectMocks
    private UserUseCase userUseCase;

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IFundPersistencePort fundPersistencePort;

    @Mock
    private ITransactionPersistencePort transactionPersistencePort;

    private User user;
    private List<Fund> funds;
    private List<Transaction> transactions;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = UserData.getUserWithSubscriptions();
        funds = FundData.getFunds();
        transactions = TransactionData.getTransactions();
    }

    @Test
    void testGetSubscriptionsByUserId() {
        when(userPersistencePort.findById(USER_ID)).thenReturn(user);
        when(fundPersistencePort.findByIds(user.getSubscriptions())).thenReturn(funds);
        when(transactionPersistencePort.findTransactionsByUserIdAndFundIdAndType(USER_ID, ID_FUND_A, "SUBSCRIPTION")).thenReturn(transactions.get(0));
        when(transactionPersistencePort.findTransactionsByUserIdAndFundIdAndType(USER_ID, ID_FUND_B, "SUBSCRIPTION")).thenReturn(transactions.get(1));

        List<Subscription> subscriptions = userUseCase.getSubscriptionsByUserId(USER_ID);

        assertNotNull(subscriptions);
        assertEquals(2, subscriptions.size());
        assertEquals(NAME_FUND_A, subscriptions.get(0).getFundName());
        assertEquals(NAME_FUND_B, subscriptions.get(1).getFundName());

        verify(userPersistencePort).findById(USER_ID);
        verify(fundPersistencePort).findByIds(user.getSubscriptions());
        verify(transactionPersistencePort, times(2)).findTransactionsByUserIdAndFundIdAndType(anyString(), anyString(), eq("SUBSCRIPTION"));
    }

    @Test
    void testGetSubscriptionsByUserId_UserNotFound() {
        when(userPersistencePort.findById(USER_ID)).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> {
            userUseCase.getSubscriptionsByUserId(USER_ID);
        });
    }
}
