package com.btg.pactual.funds_management.adapter.driving.http.util;

public class ApiConstants {
    private ApiConstants() {
        throw new IllegalStateException(UTILITY_CLASS_EXCEPTION_MESSAGE);
    }
    public static final String UTILITY_CLASS_EXCEPTION_MESSAGE = "The utility class cannot be instantiated.";

    public static final String SPRING_COMPONENT_MODEL = "spring";
    public static final String API_BASE_PATH = "/api/v1/funds";
    public static final String DEFAULT_ORDER_BY_NAME = "false";
    public static final String DEFAULT_IS_ASC = "false";
    public static final String CATEGORY_PARAM = "category";
    public static final String ORDER_BY_NAME_PARAM = "orderByName";
    public static final String IS_ASC_PARAM = "isAsc";




}
