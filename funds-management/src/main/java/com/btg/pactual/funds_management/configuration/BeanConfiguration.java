package com.btg.pactual.funds_management.configuration;

import com.btg.pactual.funds_management.adapter.driven.mongo.adapter.FundPersistenceAdapter;
import com.btg.pactual.funds_management.adapter.driven.mongo.adapter.TransactionPersistenceAdapter;
import com.btg.pactual.funds_management.adapter.driven.mongo.adapter.UserPersistenceAdapter;
import com.btg.pactual.funds_management.adapter.driven.mongo.mapper.IFundDocumentMapper;
import com.btg.pactual.funds_management.adapter.driven.mongo.mapper.ITransactionDocumentMapper;
import com.btg.pactual.funds_management.adapter.driven.mongo.mapper.IUserDocumentMapper;
import com.btg.pactual.funds_management.adapter.driven.mongo.repository.IFundRepository;
import com.btg.pactual.funds_management.adapter.driven.mongo.repository.ITransactionRepository;
import com.btg.pactual.funds_management.adapter.driven.mongo.repository.IUserRepository;
import com.btg.pactual.funds_management.domain.api.IFundServicePort;
import com.btg.pactual.funds_management.domain.api.ISubscriptionServicePort;
import com.btg.pactual.funds_management.domain.api.IUserServicePort;
import com.btg.pactual.funds_management.domain.api.usecase.FundUseCase;
import com.btg.pactual.funds_management.domain.api.usecase.SubscriptionUseCase;
import com.btg.pactual.funds_management.domain.api.usecase.UserUseCase;
import com.btg.pactual.funds_management.domain.spi.IFundPersistencePort;
import com.btg.pactual.funds_management.domain.spi.ITransactionPersistencePort;
import com.btg.pactual.funds_management.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IFundRepository fundRepository;
    private final IFundDocumentMapper fundDocumentMapper;
    private final ITransactionRepository transactionRepository;
    private final ITransactionDocumentMapper transactionDocumentMapper;
    private final IUserRepository userRepository;
    private final IUserDocumentMapper userDocumentMapper;

    @Bean
    public IFundPersistencePort fundPersistencePort() {
        return new FundPersistenceAdapter(fundRepository, fundDocumentMapper);
    }

    @Bean
    public IFundServicePort fundServicePort() {
        return new FundUseCase(fundPersistencePort());
    }

    @Bean
    public ITransactionPersistencePort transactionPersistencePort() {
        return new TransactionPersistenceAdapter( transactionRepository, transactionDocumentMapper);
    }

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserPersistenceAdapter(userRepository, userDocumentMapper);
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort(), fundPersistencePort(), transactionPersistencePort());
    }
    @Bean
    public ISubscriptionServicePort subscriptionServicePort() {
        return new SubscriptionUseCase(userPersistencePort(), fundPersistencePort(), transactionPersistencePort());
    }
}
