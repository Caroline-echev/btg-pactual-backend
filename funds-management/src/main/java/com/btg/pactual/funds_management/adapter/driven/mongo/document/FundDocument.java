package com.btg.pactual.funds_management.adapter.driven.mongo.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

import static com.btg.pactual.funds_management.adapter.driven.mongo.util.DocumentConstants.*;


@Document(collection = COLLECTION_NAME_FUND)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FundDocument {
    @Id
    private String id;

    @Field(NAME_FIELD)
    private String name;

    @Field(MINIMUM_AMOUNT_FIELD_FUND)
    private BigDecimal minimumAmount;

    @Field(CATEGORY_FIELD_FUND)
    private String category;
}