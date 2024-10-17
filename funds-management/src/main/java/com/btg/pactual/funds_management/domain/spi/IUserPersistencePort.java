package com.btg.pactual.funds_management.domain.spi;

import com.btg.pactual.funds_management.domain.model.User;

public interface IUserPersistencePort {
    User findById(String id);
    User save(User user);
}
