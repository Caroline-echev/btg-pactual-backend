package com.btg.pactual.funds_management.adapter.driven.mongo.adapter;

import com.btg.pactual.funds_management.adapter.driven.mongo.mapper.IUserDocumentMapper;
import com.btg.pactual.funds_management.adapter.driven.mongo.repository.IUserRepository;
import com.btg.pactual.funds_management.domain.model.User;
import com.btg.pactual.funds_management.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserPersistenceAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IUserDocumentMapper userDocumentMapper;
    @Override
    public User findById(String id) {
        return userDocumentMapper.mapToModel(userRepository.findById(id).orElse(null));
    }

    @Override
    public User save(User user) {
        return userDocumentMapper.mapToModel(userRepository.save(userDocumentMapper.mapToDocument(user)));
    }
}
