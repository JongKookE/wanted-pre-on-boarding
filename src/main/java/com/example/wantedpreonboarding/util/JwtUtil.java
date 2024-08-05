package com.example.wantedpreonboarding.util;

import com.example.wantedpreonboarding.JwtConstant;
import com.example.wantedpreonboarding.user.entity.UserEntity;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;

import static com.example.wantedpreonboarding.JwtConstant.*;

@Component
public class JwtUtil {
    private final SecretKey secretKey;
    private Object entity;

    @Value("${spring.jwt.token-validity-in-seconds}") long expiredTime;

    public JwtUtil(@Value("${spring.jwt.secret}") String secret) {
        // UTF8로 인코딩 하고 암호화는 HS256 방식으로 진행된다.
        // secret을 문자열로 받았기 때문에 SecretKeySpec 로 변환하는 과정이 필요
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String generateToken(UserEntity userEntity) {
        HashMap<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims(this.parseClaims(userEntity))
                .issuedAt(new Date(System.currentTimeMillis())) // jwt 발급한 시간
                .expiration(new Date(System.currentTimeMillis() + expiredTime)) // jwt 만기 시간
                .signWith(secretKey) // 해당 키로 암호화를 하겠다.
            .compact(); // jwt 생성
    }


    public HashMap<String, Object> parseClaims(UserEntity userEntity){
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(USEREMAIL.getConstant() ,userEntity.getUserEmail());
        claims.put(USERID.getConstant(), userEntity.getUserId());
        claims.put(USERNAME.getConstant(), userEntity.getUserName());
        return claims;
    }

}
