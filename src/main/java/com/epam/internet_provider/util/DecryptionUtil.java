package com.epam.internet_provider.util;

import io.vavr.control.Try;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DecryptionUtil {

    private static final int KEY_SIZE = 1024;
    private static KeyPair KEY_PAIR;

    private static void generateKeys() {
        KeyPairGenerator keyPairGenerator = Try.of(() -> KeyPairGenerator.getInstance("RSA")).get();
        keyPairGenerator.initialize(KEY_SIZE);
        KEY_PAIR = keyPairGenerator.genKeyPair();
    }

    public static String decryptString(String encrypted) {
        return Try.of(() -> {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, KEY_PAIR.getPrivate());
            return new String(cipher.doFinal(Base64.getDecoder().decode(encrypted)));
        }).getOrElseThrow((Function<Throwable, RuntimeException>) RuntimeException::new);
    }

    public static String getPublicKey() {
        if (KEY_PAIR == null) {
            generateKeys();
        }
        return Try.of(() -> Base64.getEncoder().encodeToString(KeyFactory.getInstance("RSA")
                .getKeySpec(KEY_PAIR.getPublic(), X509EncodedKeySpec.class).getEncoded())).get();
    }

}
