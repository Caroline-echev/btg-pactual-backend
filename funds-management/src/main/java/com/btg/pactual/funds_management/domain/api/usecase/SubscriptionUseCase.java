package com.btg.pactual.funds_management.domain.api.usecase;

import com.btg.pactual.funds_management.domain.api.ISubscriptionServicePort;
import com.btg.pactual.funds_management.domain.exception.InsufficientBalanceException;
import com.btg.pactual.funds_management.domain.exception.FundNotFoundException;
import com.btg.pactual.funds_management.domain.exception.UserNotFoundException;
import com.btg.pactual.funds_management.domain.model.Fund;
import com.btg.pactual.funds_management.domain.model.Transaction;
import com.btg.pactual.funds_management.domain.model.User;
import com.btg.pactual.funds_management.domain.spi.IFundPersistencePort;
import com.btg.pactual.funds_management.domain.spi.ITransactionPersistencePort;
import com.btg.pactual.funds_management.domain.spi.IUserPersistencePort;
import com.btg.pactual.funds_management.domain.util.TransactionType;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static com.btg.pactual.funds_management.domain.util.DomainConstants.*;

@RequiredArgsConstructor
public class SubscriptionUseCase implements ISubscriptionServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IFundPersistencePort fundPersistencePort;
    private final ITransactionPersistencePort transactionPersistencePort;

    @Override
    public void subscribeToFund(String userId, String fundId, boolean isSMS, BigDecimal amount) {
        User user = findUser(userId);
        Fund fund = findFund(fundId);
        validateAmount( user, fund, amount);
        User newUser = updateUser(user, fund, amount);
        userPersistencePort.save(newUser);
        Transaction transaction = buildTransaction(user, fund, amount);
        transactionPersistencePort.save(transaction);


    }
    private User findUser(String userId) {
        User user = userPersistencePort.findById(userId);
        if(user == null) {
            throw new UserNotFoundException(USER_NOT_FOUND_EXCEPTION_MESSAGE);
        }
        return user;
    }
    private Fund findFund(String fundId) {
        Fund fund = fundPersistencePort.findById(fundId);
        if(fund == null) {
            throw new FundNotFoundException(FUND_NOT_FOUND_EXCEPTION_MESSAGE);
        }
        return fund;
    }
    private void validateAmount(User user, Fund fund, BigDecimal amount) {
        if(amount.compareTo(user.getInitialBalance()) > 0 && amount.compareTo(fund.getMinimumAmount()) < 0) {
            throw new InsufficientBalanceException( INSUFFICIENT_BALANCE + fund.getName());
        }
    }
    private Transaction buildTransaction(User user, Fund fund, BigDecimal amount) {
        Transaction transaction = new Transaction();
        transaction.setUserId(user.getId());
        transaction.setFundId(fund.getId());
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.SUBSCRIPTION.name());
        return transaction;
    }
    private User updateUser(User user, Fund fund, BigDecimal amount) {
        user.setInitialBalance(user.getInitialBalance().subtract(amount));
        user.getSubscriptions().add(fund.getId());
        return user;
    }

}

