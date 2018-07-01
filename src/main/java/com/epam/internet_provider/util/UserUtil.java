package com.epam.internet_provider.util;

import com.epam.internet_provider.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserUtil {

    private static final int DEF_ROLE = 0;
    private static final int DEF_STATUS = 0;
    private static final int DEF_AMOUNT = 0;

    public static User createDefaultUser(Map<String, String[]> parameters) {
        User user = new User();
        user.setLogin(Stream.of(parameters.get("login")).findFirst().orElse(null));
        user.setPassword(Stream.of(parameters.get("password")).findFirst().orElse(null));
        user.setEmail(Stream.of(parameters.get("email")).findFirst().orElse(null));
        user.setRole(DEF_ROLE);
        user.setStatus(DEF_STATUS);
        user.setBonus_amount(DEF_AMOUNT);
        return user;
    }
}
