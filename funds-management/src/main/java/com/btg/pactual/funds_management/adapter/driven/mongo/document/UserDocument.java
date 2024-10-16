package com.btg.pactual.funds_management.adapter.driven.mongo.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDocument {
    @Id
    private String id;
    @Field("name")
    private String name;
    @Field("email")
    private String email;
    @Field("phone")
    private String phone;
    @Field("initial_balance")
    private BigDecimal initialBalance;
    @Field("date_of_birth")
    private LocalDateTime dateOfBirth;
    @Field("subscriptions")
    private List<String> subscriptions;
}
