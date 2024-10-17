package com.btg.pactual.funds_management.adapter.driven.mongo.repository;

import com.btg.pactual.funds_management.adapter.driven.mongo.document.TransactionDocument;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ITransactionRepository extends MongoRepository<TransactionDocument, String> {
    @Query("{ 'user_id': ?0, 'fund_id': ?1, 'transaction_type': ?2 }")
    TransactionDocument findByUserIdAndFundIdAndTransactionType(String userId, String fundId, String transactionType);

}
