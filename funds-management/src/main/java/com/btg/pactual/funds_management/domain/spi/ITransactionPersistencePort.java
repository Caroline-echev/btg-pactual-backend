package com.btg.pactual.funds_management.domain.spi;

import com.btg.pactual.funds_management.domain.model.Transaction;

import java.util.List;

public interface ITransactionPersistencePort {
    Transaction save(Transaction transaction);
    Transaction findTransactionsByUserIdAndFundIdAndType(String userId, String fundId, String type);
    List<Transaction> findTransactionByUserId(String userId);
}
