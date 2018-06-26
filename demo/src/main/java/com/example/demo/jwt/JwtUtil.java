package com.example.demo.jwt;

import com.example.demo.domain.SysUser;
import com.example.demo.dto.properties.SecurityProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class JwtUtil {

    /*private static final String CLAIM_KEY_USER_ACCOUNT = "sub";
    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret; //秘钥

    @Value("${jwt.expiration}")
    private Long expiration; //过期时间*/

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 从token中获取claims
     * @param request
     * @return
     */
    private Claims getClaimsFromToken(HttpServletRequest request){

        String header = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(header, "Bearer ");

        Claims claims;
        try{
            claims = Jwts.parser()
                    .setSigningKey(securityProperties.getOauth2().getJwtSigningKey().getBytes("UTF-8"))
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            claims = null;
        }
        return claims;
    }

    /**
     * 从token中获取用户account
     * @param request
     * @return
     */
    public  String getUserAccountFromToken(HttpServletRequest request){
        String useraccount;
        try{
            final Claims claims = getClaimsFromToken(request);
            useraccount = (String)claims.get("user_name");
        }catch (Exception e){
            useraccount = null;
        }
        return useraccount;
    }

    /**
     * 从token中获取用户name
     * @param request
     * @return
     */
    public String getNameFromToken(HttpServletRequest request){
        String name;
        try{
            final Claims claims = getClaimsFromToken(request);
            name = (String)claims.get("name");
        }catch (Exception e){
            name = null;
        }
        return name;
    }

    /**
     * 从token中获取用户phone
     * @param request
     * @return
     */
    public String getPhoneFromToken(HttpServletRequest request){
        String phone;
        try{
            final Claims claims = getClaimsFromToken(request);
            phone = (String)claims.get("phone");
        }catch (Exception e){
            phone = null;
        }
        return phone;
    }

    /**
     * 从token中获取用户email
     * @param request
     * @return
     */
    public String getEmailFromToken(HttpServletRequest request){
        String email;
        try{
            final Claims claims = getClaimsFromToken(request);
            email = (String)claims.get("email");
        }catch (Exception e){
            email = null;
        }
        return email;
    }

    /**
     * 从token中获取用户authorities
     * @param request
     * @return
     */
    public List<String> getAuthoritiesFromToken(HttpServletRequest request){
        List<String> authorities = new ArrayList<>();
        try{
            final Claims claims = getClaimsFromToken(request);
            authorities = claims.get("authorities", List.class);
        }catch (Exception e){
            authorities = null;
        }
        return authorities;
    }



    /**
     * 从token中获取创建时间
     * @param token
     * @return
     *//*
    public Date getCreatedDateFromToken(String token){
        Date created;
        try{
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long)claims.get(CLAIM_KEY_CREATED));
        }catch (Exception e){
            created = null;
        }
        return created;
    }

    *//**
     * 获取token的过期时间
     * @param token
     * @return
     *//*
    public Date getExpirationDateFromToken(String token){
        Date expiration;
        try{
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        }catch (Exception e){
            expiration = null;
        }
        return expiration;
    }

    *//**
     * 生存token的过期时间
     * @return
     *//*
    private Date generateExpirationDate(){
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    *//**
     * 判断token是否过期
     * @param token
     * @return
     *//*
    private Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        Boolean result = expiration.before(new Date());
        return result;
    }

    *//**
     * 生成token
     * @param userDetails
     * @return
     *//*
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USER_ACCOUNT, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());

        return generateToken(claims);
    }

    String generateToken(Map<String, Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    *//**
     * token 是否可刷新
     * @param token
     * @return
     *//*
    public Boolean canTokenBeRefreshed(String token){
        final Date created = getCreatedDateFromToken(token);
        return !isTokenExpired(token);
    }

    *//**
     * 刷新token
     * @param token
     * @return
     *//*
    public String refreshToken(String token){
        String refreshedToken;
        try{
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        }catch (Exception e){
            refreshedToken = null;
        }
        return refreshedToken;
    }

    *//**
     * 验证token
     * @param token
     * @param userDetails
     * @return
     *//*
    public Boolean validateToken(String token, UserDetails userDetails){
        SysUser user = (SysUser) userDetails;
        final String useraccount = getUserAccountFromToken(token);
        final Date created = getCreatedDateFromToken(token);
        Boolean result = (
                useraccount.equals(user.getUsername()) && !isTokenExpired(token)
                );
        return result;
    }*/

}
