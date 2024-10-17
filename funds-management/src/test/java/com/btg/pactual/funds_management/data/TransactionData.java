package com.btg.pactual.funds_management.data;

import com.btg.pactual.funds_management.domain.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class TransactionData {

    public static Transaction getTransactionA() {
        return new Transaction("T1", "userId", "1", "SUBSCRIPTION", BigDecimal.valueOf(100), LocalDateTime.now());
    }

    public static Transaction getTransactionB() {
        return new Transaction("T2", "userId", "2", "SUBSCRIPTION", BigDecimal.valueOf(200), LocalDateTime.now());
    }
    public static List<Transaction> getTransactions() {
        return List.of(getTransactionA(), getTransactionB());
    }
}
