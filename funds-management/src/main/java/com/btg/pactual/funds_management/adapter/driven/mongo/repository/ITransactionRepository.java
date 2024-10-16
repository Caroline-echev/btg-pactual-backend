package com.btg.pactual.funds_management.adapter.driven.mongo.repository;

import com.btg.pactual.funds_management.adapter.driven.mongo.document.TransactionDocument;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ITransactionRepository extends MongoRepository<TransactionDocument, String> {
}
