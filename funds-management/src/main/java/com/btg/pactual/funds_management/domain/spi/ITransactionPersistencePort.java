package com.btg.pactual.funds_management.domain.spi;

import com.btg.pactual.funds_management.domain.model.Transaction;

public interface ITransactionPersistencePort {
    Transaction save(Transaction transaction);
}
