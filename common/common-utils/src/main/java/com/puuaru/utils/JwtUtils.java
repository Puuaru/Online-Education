package com.puuaru.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.SneakyThrows;
import org.springframework.util.ObjectUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

/**
 * @Description: JwtUtils
 * @Author: puuaru
 * @Date: 2023/1/20
 */
public class JwtUtils {
    public static final long TTL_MILLIS = 1000 * 60 * 60 * 24;  // 登录token的过期时间
    public static final String APP_KEY = "OnlineEducation";

    /**
     * 通过base64解码的方式对密钥进行加密
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtils.APP_KEY);
        SecretKeySpec key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    public static String generateJwt(String id, String nickname) {
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS512;
        Long nowMillis = System.currentTimeMillis();
        Date nowDate = new Date(nowMillis);
        long expMillis = nowMillis + TTL_MILLIS;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject("online-user")
                .setIssuer("puuaru")
                .setIssuedAt(nowDate)
                .setExpiration(expDate)
                .claim("id", id)
                .claim("nickname", nickname)
                .signWith(algorithm, generalKey())
                .compact();
    }

    /**
     * 检查Jwt的存在性及有效性
     * @param request 传入HttpRequest请求
     * @return
     */
    public static boolean checkToken(HttpServletRequest request) {
        try {
            String jwt = request.getHeader("token");
            if (ObjectUtils.isEmpty(jwt))
                return false;
            parseJwt(jwt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    /**
     * 检查Jwt的存在性及有效性
     * @param jwt 直接传入jwt
     * @return
     */
    public static boolean checkToken(String jwt) {
        if (ObjectUtils.isEmpty(jwt))
            return false;
        try {
            parseJwt(jwt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    /**
     * 从Jwt中获取载荷
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJwt(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    /**
     * 从请求携带的jwt中获取会员id
     * @param request
     * @return
     */
    @SneakyThrows
    public static String getMemberIdByJwt(HttpServletRequest request) {
        String jwt = request.getHeader("token");
        if (ObjectUtils.isEmpty(jwt)) {
            return "";
        }
        Claims body = parseJwt(jwt);
        return (String) body.get("id");
    }
}
