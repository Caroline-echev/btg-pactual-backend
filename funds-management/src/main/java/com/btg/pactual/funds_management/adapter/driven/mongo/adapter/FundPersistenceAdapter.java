package com.btg.pactual.funds_management.adapter.driven.mongo.adapter;

import com.btg.pactual.funds_management.adapter.driven.mongo.document.FundDocument;
import com.btg.pactual.funds_management.adapter.driven.mongo.mapper.IFundDocumentMapper;
import com.btg.pactual.funds_management.adapter.driven.mongo.repository.IFundRepository;
import com.btg.pactual.funds_management.domain.model.Fund;
import com.btg.pactual.funds_management.domain.spi.IFundPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FundPersistenceAdapter implements IFundPersistencePort {
    private final IFundRepository  fundRepository;
    private final IFundDocumentMapper fundDocumentMapper;

    @Override
    public List<Fund> findAll() {
        List<FundDocument> fundDocuments= fundRepository.findAll();

        return fundDocuments.stream().map(fundDocumentMapper::mapToModel).toList();
    }

    @Override
    public List<Fund> findByCategory(String category) {
        return fundRepository.findByCategory(category).stream().map(fundDocumentMapper::mapToModel).toList();
    }

    @Override
    public Fund findById(String fundId) {
        FundDocument fund = fundRepository.findById(fundId).orElse(null);
        return fundDocumentMapper.mapToModel(fund);
    }

    @Override
    public List<Fund> findByIds(List<String> fundsId) {
        List<FundDocument> funds = fundRepository.findByIdIn(fundsId);
        return funds.stream().map(fundDocumentMapper::mapToModel).toList();
    }
}
