package com.btg.pactual.funds_management.data;

import com.btg.pactual.funds_management.adapter.driven.mongo.document.FundDocument;
import com.btg.pactual.funds_management.adapter.driving.http.dto.response.FundResponse;
import com.btg.pactual.funds_management.domain.model.Fund;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class FundData {

    public static String  CATEGORY_1 = "Category 1";
    public static String  CATEGORY_2 = "Category 2";
    public static String  NAME_FUND_A = "Fund A";
    public static String  NAME_FUND_B = "Fund B";
    public static String  NAME_FUND_C = "Fund C";
    public static String  ID_FUND_A = "1";
    public static String  ID_FUND_B = "2";
    public static List<Fund> getFunds() {
        return Arrays.asList(
                new Fund("1", "Fund A", new BigDecimal("100"), "Category 1"),
                new Fund("2", "Fund B", new BigDecimal("200"), "Category 2"),
                new Fund("3", "Fund C", new BigDecimal("150"), "Category 1")
        );
    }

    public static List<FundResponse> getFundsResponse() {
        return Arrays.asList(
                new FundResponse("1", "Fund A", new BigDecimal("100"), "Category 1"),
                new FundResponse("2", "Fund B", new BigDecimal("200"), "Category 2"),
                new FundResponse("3", "Fund C", new BigDecimal("150"), "Category 1")
        );
    }
    public static List<FundDocument> getFundDocuments() {
        return Arrays.asList(
                new FundDocument("1", "Fund A", new BigDecimal("100"), "Category 1"),
                new FundDocument("2", "Fund B", new BigDecimal("200"), "Category 2"),
                new FundDocument("3", "Fund C", new BigDecimal("150"), "Category 1")
        );
    }
    public static Fund getFundA() {
        return new Fund("1", "Fund A", new BigDecimal("100"), "Category 1");
    }
    public static Fund getFundB() {
        return new Fund("2", "Fund B", new BigDecimal("200"), "Category 2");
    }
    public static Fund getFundC() {
        return new Fund("3", "Fund C", new BigDecimal("150"), "Category 1");
    }
}
