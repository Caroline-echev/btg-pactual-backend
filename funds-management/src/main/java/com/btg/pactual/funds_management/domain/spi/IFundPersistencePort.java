package com.btg.pactual.funds_management.domain.spi;

import com.btg.pactual.funds_management.domain.model.Fund;

import java.util.List;

public interface IFundPersistencePort{
    List<Fund> findAll();
    List<Fund> findByCategory(String category);
}
