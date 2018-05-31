package com.fancy.library.utils.encrypt;

import android.support.annotation.NonNull;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Digest
 *
 * @author fanlei
 * @version 1.0 2018\5\31 0031
 * @since JDK 1.7
 */
public class Digest {
    public static final Digest MD5 = new Digest("MD5");
    public static final Digest SHA256 = new Digest("SHA-256");

    private static final Charset CHARSET_UTF_8 = Charset.forName("UTF-8");

    private final String algorithm;

    private Digest(@NonNull String algorithm) {
        this.algorithm = algorithm;
    }

    private byte[] getRaw(@NonNull byte[] data) {
        try {
            return MessageDigest.getInstance(algorithm).digest(data);
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    public byte[] getRaw(@NonNull String data) {
        return getRaw(data.getBytes(CHARSET_UTF_8));
    }

    private String getHex(@NonNull byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : getRaw(data)) {
            sb.append(String.format("%02x", 0xFF & b));
        }
        return sb.toString();
    }

    public String getHex(@NonNull String data) {
        return getHex(data.getBytes(CHARSET_UTF_8));
    }

}
