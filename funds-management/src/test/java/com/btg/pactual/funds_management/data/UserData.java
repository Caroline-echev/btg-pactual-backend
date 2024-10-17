package com.btg.pactual.funds_management.data;

import com.btg.pactual.funds_management.domain.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserData {
    public static String USER_ID = "userId";
    public static User getUser() {
        return new User("userId", "name", "email", "phone", BigDecimal.valueOf(500), LocalDateTime.now(), new ArrayList<>());
    }

    public static User getUserWithSubscriptions(String userId) {
        List<String> subscriptions = Arrays.asList(FundData.ID_FUND_A, FundData.ID_FUND_B);
        return new User("userId", "name", "email", "phone", BigDecimal.valueOf(500), LocalDateTime.now(), subscriptions);
    }
    public static User getUserWithSubscriptions() {
        List<String> subscriptions = Arrays.asList(FundData.ID_FUND_A, FundData.ID_FUND_B);
        return new User("userId", "name", "email", "phone", BigDecimal.valueOf(500), LocalDateTime.now(), subscriptions);
    }
}
