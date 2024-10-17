package com.btg.pactual.funds_management.data;

import com.btg.pactual.funds_management.domain.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class UserData {
    public static String USER_ID = "userId";
    public static User getUser() {
        return new User("userId", "name", "email", "phone", BigDecimal.valueOf(500), LocalDateTime.now(), new ArrayList<>());
    }
}
