package com.kano.security.token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * @title com.kano.security.token.JwtGenerate
 * @description
 *        <pre>
 *          jwt生成
 *        </pre>
 *
 * @author soranico
 * @version 1.0
 * @date 2020/8/16
 *
 * </pre>
 */
@Component
public class JwtGenerate {

    private static String secretKey;
    private static Integer expireTime;

    public static final String TOKEN = "token";
    private static final String DEFAULT_SECRET_KEY = "letter-song";
    private static byte[] secretKeyBytes;
    private static final int DEFAULT_EXPIRE_TIME = 3600 * 24 * 7;

    private static final Map<String, Object> header = new HashMap<>(4);


    static {
        header.put("alg", "HS256");
        header.put("typ", "JWT");
    }

    public JwtGenerate(@Value("${kano.security.secret-key:}") String secretKey,
                       @Value("${kano.security.expire-Time:-1}") Integer expireTime) {

        JwtGenerate.secretKey = secretKey.isEmpty() ? DEFAULT_SECRET_KEY : secretKey;
        JwtGenerate.secretKeyBytes = TextCodec.BASE64.decode(JwtGenerate.secretKey);
        JwtGenerate.expireTime = (expireTime == -1 ? DEFAULT_EXPIRE_TIME : expireTime) * 1000;
    }

    public static String generateToken(String username, String roles) {
        Map<String, Object> payLoad = new HashMap<>(8);
        payLoad.put("sub", "teriri");
        payLoad.put("iss", "soranico");
        payLoad.put("exp", System.currentTimeMillis() + expireTime);
        payLoad.put("username", username);
        payLoad.put("roles", roles);
        return Jwts.builder()
                .setHeader(header)
                .addClaims(payLoad)
                .signWith(SignatureAlgorithm.HS256, secretKeyBytes)
                .compact();

    }

}




