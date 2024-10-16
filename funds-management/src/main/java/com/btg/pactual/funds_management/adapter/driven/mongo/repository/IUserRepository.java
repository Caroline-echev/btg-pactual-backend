package com.btg.pactual.funds_management.adapter.driven.mongo.repository;

import com.btg.pactual.funds_management.adapter.driven.mongo.document.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<UserDocument, String> {

}
