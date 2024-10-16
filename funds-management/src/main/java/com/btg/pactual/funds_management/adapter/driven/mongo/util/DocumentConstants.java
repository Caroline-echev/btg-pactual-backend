package com.btg.pactual.funds_management.adapter.driven.mongo.util;

public class DocumentConstants {

    private DocumentConstants() {
        throw new IllegalStateException(UTILITY_CLASS_EXCEPTION_MESSAGE);
    }
    public static final String UTILITY_CLASS_EXCEPTION_MESSAGE = "The utility class cannot be instantiated.";

    public static final String COLLECTION_NAME_FUND = "fund";
    public static final String ID_FIELD = "_id";
    public static final String NAME_FIELD = "name";
    public static final String MINIMUM_AMOUNT_FIELD_FUND = "minimum_amount";
    public static final String CATEGORY_FIELD_FUND = "category";
}
