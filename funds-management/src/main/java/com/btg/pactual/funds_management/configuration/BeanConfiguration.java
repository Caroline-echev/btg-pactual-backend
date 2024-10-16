package com.btg.pactual.funds_management.configuration;

import com.btg.pactual.funds_management.adapter.driven.mongo.adapter.FundPersistenceAdapter;
import com.btg.pactual.funds_management.adapter.driven.mongo.mapper.IFundDocumentMapper;
import com.btg.pactual.funds_management.adapter.driven.mongo.repository.IFundRepository;
import com.btg.pactual.funds_management.domain.api.IFundServicePort;
import com.btg.pactual.funds_management.domain.api.usecase.FundUseCase;
import com.btg.pactual.funds_management.domain.spi.IFundPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IFundRepository fundRepository;
    private final IFundDocumentMapper fundDocumentMapper;
    @Bean
    public IFundPersistencePort fundPersistencePort() {
        return new FundPersistenceAdapter(fundRepository, fundDocumentMapper);
    }

    @Bean
    public IFundServicePort fundServicePort() {
        return new FundUseCase(fundPersistencePort());
    }

}
