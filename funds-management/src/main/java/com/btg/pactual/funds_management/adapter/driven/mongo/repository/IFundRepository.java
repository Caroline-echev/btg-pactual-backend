package com.btg.pactual.funds_management.adapter.driven.mongo.repository;

import com.btg.pactual.funds_management.adapter.driven.mongo.document.FundDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IFundRepository extends MongoRepository<FundDocument, String> {
    List<FundDocument> findByCategory(String category);
    List<FundDocument> findByIdIn(List<String> fundIds);
}