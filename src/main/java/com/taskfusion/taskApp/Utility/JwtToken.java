package com.taskfusion.taskApp.Utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtToken {

    private static final String secretKey = "5171488efc1d8a4f2f07edc493851cbeb6adf7238e8e46964f04c8216116de87";
    private static final Key securekey = Keys.hmacShaKeyFor(secretKey.getBytes());

    public static String generateToken(String username) {
    Claims claims=Jwts.claims().setSubject(username);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
//                .signWith(SignatureAlgorithm.HS256, secretKey)
                .signWith(securekey)
                .compact();
    }

    public static Key getSecurekey(){
        return securekey;
    }


}
