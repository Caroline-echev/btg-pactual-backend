package com.btg.pactual.funds_management.adapter.driven.mongo.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "transaction")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionDocument {
    @Id
    private String id;

    @Field("user_id")
    private String userId;

    @Field("fund_id")
    private String fundId;

    @Field("transaction_type")
    private String transactionType;

    @Field("amount")
    private BigDecimal amount;

    @Field("date_time")
    private LocalDateTime dateTime;

}