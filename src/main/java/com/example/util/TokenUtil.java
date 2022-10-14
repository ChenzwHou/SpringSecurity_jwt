package com.example.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtil {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("{jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(UserDetails details){
        Map<String,Object> map=new HashMap<>(2);
        map.put("username",details.getUsername());
        map.put("created",new Date());
        return this.generateToken(map);
    }


    public String generateToken(Map<String ,Object> map){
        return Jwts.builder()
                .setClaims(map)
                .signWith(SignatureAlgorithm.HS512,secret)
                .setExpiration(new Date(System.currentTimeMillis()+expiration*1000))
                .compact();
    }

    public Claims getTokenBody(String token){
        try {
            return Jwts.parser().setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            return null;
        }
    }


    public String getUsernameByToken(String token){
        return (String) this.getTokenBody(token).get("username");
    }


   public boolean isExpiration(String  token){
        return this.getTokenBody(token).getExpiration().before(new Date());
   }


    public String isRefreshToken(String  token){
        Claims claims = this.getTokenBody(token);
        claims.setExpiration(new Date());
        return this.generateToken(claims);
    }
}
