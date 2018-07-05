package com.epam.internet_provider.util;

import com.epam.internet_provider.model.Credentials;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JsonUtil {

    public static Credentials parseCredentials(BufferedReader requestBuffer) {
        return Optional.of(new JSONObject(requestBuffer.lines()
                .reduce("", (accumulator, actual) -> accumulator + actual)))
                .map(json -> new Credentials(json.getString("login"), json.getString("password")))
                .get();
    }

}
