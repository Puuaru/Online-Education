package com.puuaru.gateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


/**
 * @Description: JwtAccessManager
 * @Author: puuaru
 * @Date: 2023/6/9
 */
@Component
@Primary
public class JwtAccessManager implements ReactiveAuthorizationManager<AuthorizationContext> {
    public static final String APP_KEY = "OnlineEducation";

    @SneakyThrows
    @Override
    public Mono<AuthorizationDecision> check(Mono authentication, AuthorizationContext context) {
        String jwt = context.getExchange().getRequest().getHeaders().getFirst("token");
        String token = getMemberIdByJwt(jwt);
        System.out.println("==========" + token + "==========");
        return authentication.just(new AuthorizationDecision(true));
    }

    public static String getMemberIdByJwt(String jwt) throws Exception {
        if (ObjectUtils.isEmpty(jwt)) {
            return "";
        }
        Claims body = parseJwt(jwt);
        return (String) body.get("id");
    }
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(APP_KEY);
        SecretKeySpec key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }
    public static Claims parseJwt(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
