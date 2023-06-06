package com.albaco.depositorigination.common.utils;

import java.security.SecureRandom;

public class CommonUtils {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static boolean isStringEmpty(String str) {
        if (str == null || str.isEmpty())
            return true;
        return false;
    }

    public static String generateRandomStringWithPrefix(int length, String prefix) throws Exception {
        SecureRandom random = new SecureRandom();
        int remainingLen = 0;
        if (CommonUtils.isStringEmpty(prefix))
            remainingLen = length;
        else {
            if (length < prefix.length())
                throw new Exception("Prefix length cannot be greater than the length provided");
            remainingLen = length - prefix.length();
        }
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < remainingLen; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }

    public static Double getDoubleForStringNullable(String value) {
        try {
            return Double.valueOf(value);
        } catch (NumberFormatException | NullPointerException e) {
            return null;
        }
    }
}
