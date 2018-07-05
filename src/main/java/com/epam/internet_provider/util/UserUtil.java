package com.epam.internet_provider.util;

import com.epam.internet_provider.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserUtil {

    private static final int DEFAULT_ROLE = 0;
    private static final int DEFAULT_STATUS = 0;
    private static final int DEFAULT_AMOUNT = 0;

    public static User createDefaultUser(JSONObject userJson) {
        User user = new User();
        user.setLogin(userJson.getString("login"));
        user.setPassword(HashingUtil.hashString(DecryptionUtil.decryptString(userJson.getString("password"))));
        user.setEmail(userJson.getString("email"));
        user.setRole(DEFAULT_ROLE);
        user.setStatus(DEFAULT_STATUS);
        user.setBonus_amount(DEFAULT_AMOUNT);
        return user;
    }
}
