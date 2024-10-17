package com.btg.pactual.funds_management.domain.api.usecase;

import com.btg.pactual.funds_management.domain.api.IUserServicePort;
import com.btg.pactual.funds_management.domain.exception.UserNotFoundException;
import com.btg.pactual.funds_management.domain.model.Fund;
import com.btg.pactual.funds_management.domain.model.Subscription;
import com.btg.pactual.funds_management.domain.model.Transaction;
import com.btg.pactual.funds_management.domain.model.User;
import com.btg.pactual.funds_management.domain.spi.IFundPersistencePort;
import com.btg.pactual.funds_management.domain.spi.ITransactionPersistencePort;
import com.btg.pactual.funds_management.domain.spi.IUserPersistencePort;
import com.btg.pactual.funds_management.domain.util.TransactionType;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.btg.pactual.funds_management.domain.util.DomainConstants.USER_NOT_FOUND_EXCEPTION_MESSAGE;

@RequiredArgsConstructor
public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final IFundPersistencePort fundPersistencePort;
    private final ITransactionPersistencePort transactionPersistencePort;
    @Override
    public List<Subscription> getSubscriptionsByUserId(String userId) {
        User user = getUser(userId);
        List<String> fundIds = user.getSubscriptions();
        List<Fund> funds = fundPersistencePort.findByIds(fundIds);
        List<Transaction> transactions = fundIds.stream()
                .map(fundId -> transactionPersistencePort
                        .findTransactionsByUserIdAndFundIdAndType(userId, fundId, TransactionType.SUBSCRIPTION.name())).toList();

        return buildSubscription(user,funds, transactions);

    }
    private List<Subscription> buildSubscription(User user, List<Fund> funds, List<Transaction> transactions) {
        List<Subscription> subscriptions = new ArrayList<>();
        Map<String, Fund> fundMap = funds.stream()
                .collect(Collectors.toMap(Fund::getId, fund -> fund));

        for (Transaction transaction : transactions) {
            Fund fund = fundMap.get(transaction.getFundId());
            if (fund != null) {
                Subscription subscription = new Subscription(
                        user.getId(),
                        fund.getId(),
                        fund.getName(),
                        transaction.getAmount(),
                        transaction.getTransactionType(),
                        transaction.getDateTime()
                );
                subscriptions.add(subscription);
            }
        }

        return subscriptions;
    }


    private User getUser(String userId) {
        User user = userPersistencePort.findById(userId);
        if(user == null) {
            throw new UserNotFoundException(USER_NOT_FOUND_EXCEPTION_MESSAGE);
        }
        return user;
    }
}
