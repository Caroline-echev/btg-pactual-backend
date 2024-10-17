package com.btg.pactual.funds_management.adapter.driven.mongo.adapter;

import com.btg.pactual.funds_management.adapter.driven.mongo.mapper.ITransactionDocumentMapper;
import com.btg.pactual.funds_management.adapter.driven.mongo.repository.ITransactionRepository;
import com.btg.pactual.funds_management.domain.model.Transaction;
import com.btg.pactual.funds_management.domain.spi.ITransactionPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TransactionPersistenceAdapter implements ITransactionPersistencePort {
    private final ITransactionRepository transactionRepository;
    private final ITransactionDocumentMapper transactionDocumentMapper;


    @Override
    public Transaction save(Transaction transaction) {
        return transactionDocumentMapper.mapToModel(transactionRepository.save(transactionDocumentMapper.mapToDocument(transaction)));
    }

    @Override
    public Transaction findTransactionsByUserIdAndFundIdAndType(String userId, String fundId, String type) {
        return transactionDocumentMapper.mapToModel(transactionRepository.findByUserIdAndFundIdAndTransactionType(userId, fundId, type));}

    @Override
    public List<Transaction> findTransactionByUserId(String userId) {
        return transactionRepository.findByUserId(userId).stream().map(transactionDocumentMapper::mapToModel).toList();
    }
}
