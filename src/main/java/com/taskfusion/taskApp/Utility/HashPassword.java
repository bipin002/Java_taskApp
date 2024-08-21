package com.taskfusion.taskApp.Utility;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class HashPassword {
    public static String hassPassword(String Forhashing) throws NoSuchAlgorithmException {
        StringBuilder hashpassword = new StringBuilder();
        MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash = msgDigest.digest(Forhashing.getBytes());

        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hashpassword.append("0");
            }
            hashpassword.append(hex);
        }
        return hashpassword.toString();
    }
}
