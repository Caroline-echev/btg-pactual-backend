package com.btg.pactual.funds_management.domain.api.usecase;

import com.btg.pactual.funds_management.domain.api.ISubscriptionServicePort;
import com.btg.pactual.funds_management.domain.exception.AlreadySubscribedException;
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
        validateSubscription(user, fund);
        validateAmount( user, fund, amount);
        User newUser = updateUserSubscribe(user, fund, amount);
        userPersistencePort.save(newUser);
        Transaction transaction = buildTransaction(user, fund, amount, TransactionType.SUBSCRIPTION.name());
        transactionPersistencePort.save(transaction);


    }

    @Override
    public void unsubscribeToFund(String userId, String fundId, boolean isSMS) {
        User user = findUser(userId);
        Fund fund = findFund(fundId);
        Transaction transaction = transactionPersistencePort.findTransactionsByUserIdAndFundIdAndType(userId, fundId, TransactionType.SUBSCRIPTION.name());
        User newUser = updateUserUnsubscribe(user, fund, transaction.getAmount());
        userPersistencePort.save(newUser);
        Transaction newTransaction = buildTransaction(user, fund, transaction.getAmount(), TransactionType.CANCELLATION.name());
        transactionPersistencePort.save(newTransaction);
    }

    private void validateSubscription(User user, Fund fund) {
        if (user.getSubscriptions().contains(fund.getId())) {
            throw new AlreadySubscribedException(ALREADY_SUBSCRIBED_EXCEPTION_MESSAGE + fund.getName());
        }
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
        if (amount.compareTo(fund.getMinimumAmount()) < VALIDATION_MINIMUM_AMOUNT_ZERO) {
            throw new InsufficientBalanceException(INSUFFICIENT_AMOUNT + fund.getName());
        }

        if (amount.compareTo(user.getInitialBalance()) > VALIDATION_MINIMUM_AMOUNT_ZERO) {
            throw new InsufficientBalanceException(INSUFFICIENT_BALANCE + fund.getName());
        }
    }
    private Transaction buildTransaction(User user, Fund fund, BigDecimal amount, String transactionType) {
        Transaction transaction = new Transaction();
        transaction.setUserId(user.getId());
        transaction.setFundId(fund.getId());
        transaction.setAmount(amount);
        transaction.setTransactionType(transactionType);
        return transaction;
    }
    private User updateUserSubscribe(User user, Fund fund, BigDecimal amount) {
        user.setInitialBalance(user.getInitialBalance().subtract(amount));
        user.getSubscriptions().add(fund.getId());
        return user;
    }
    private User updateUserUnsubscribe(User user, Fund fund, BigDecimal amount) {
        user.setInitialBalance(user.getInitialBalance().add(amount));
        user.getSubscriptions().remove(fund.getId());
        return user;
    }

}

