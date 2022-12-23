package com.blockchain.config.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
* JwtToken的工具类
*
* */
@Component
public class JwtTokenUtil {

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";

    /**
     * 密钥
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * 失效时间
     */
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 根据用户信息生成token
     * @param userDetails
     */
    public String generateToken(UserDetails userDetails) {
        HashMap<String, Object> claim = new HashMap<>();
        claim.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claim.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claim);
    }

    /**
     * 从token中获取登录用户名
     * @param token
     * @return
     */
    public String getUserNameFromToken(String token) {
        String username;
        try {
            //从token中获取荷载
            Claims claims = getClaimsFromToken(token);
            //从荷载中获取用户名
            username = claims.getSubject();
            //System.out.println(username);
        } catch (Exception e) {
            //如果有异常 username就为空
            username = null;
        }
        return username;
    }

    /**
     * 判断token是否有效
     * 1、token是否已经过期？
     * 2、token荷载中的用户名是否和userDetails里的用户名一致？
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token,UserDetails userDetails){
        String userNameFromToken = getUserNameFromToken(token);
        return userNameFromToken.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 判断token是否可以被刷新
     * @param token
     * @return
     */
    public boolean canRefresh(String token){
        return !isTokenExpired(token);
    }

    /**
     * 刷新token，把创建时间改成当前时间
     * @param token
     * @return
     */
    public String refreshToken(String token){
        Claims claimsFromToken = getClaimsFromToken(token);
        claimsFromToken.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claimsFromToken);
    }

    /**
     * 判断token是否有效
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        Date expireDate = getExpireDateFromToken(token);
        //看token中的date是否在当前时间前面，如果是则就失效
        return expireDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     * @param token
     * @return
     */
    private Date getExpireDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 根据token获取荷载
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            e.printStackTrace();
        }
        return claims;
    }


    /**
     * 根据荷载生成jwttoken
     * @param claims
     * @return
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                //放入荷载
                .setClaims(claims)
                //失效时间 需要将long类型转换一下;
                .setExpiration(generateExpirationDate())
                //签名
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 转换过期时间成data类型
     * @return
     */
    private Date generateExpirationDate() {
        //当前时间+配置中的失效时间
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }
}
