package com.xinyu.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
    //设置密钥
    private static final String SECRET = "xinyu";

    //获取token
    public static String getToken(Integer id,String username,String password){

        //创建集合用于装接收的数据
        Map<String,Object> claims = new HashMap<String,Object>();
        claims.put("id",id);
        claims.put("username",username);
        claims.put("password",password);

        //创建jwt对象
        JwtBuilder jwtBuilder = Jwts.builder();

        //签发算法，设置密钥
        jwtBuilder.signWith(SignatureAlgorithm.HS512,SECRET);

        //添加数据
        jwtBuilder.setClaims(claims);

        //设置签发时间
        jwtBuilder.setIssuedAt(new Date(System.currentTimeMillis()));

        //设置过期时间-5秒
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + 1000*5));

        //设置签发人
        jwtBuilder.setSubject(id.toString());

        //生成token
        return jwtBuilder.compact();
    }

    //解析token
    public static Map<String,Object> parseToken(String token){
        Jwt jwt = Jwts.parser().setSigningKey(SECRET).parse(token);
        Map<String,Object> claims = (Map<String,Object>)jwt.getBody();
        return claims;
    }

    public static void main(String[] args) {
        String token = JWTUtils.getToken(2,"admin","111111");
        System.out.println(token);
        Map<String,Object> claims = JWTUtils.parseToken(token);
        String name = (String) claims.get("username");
        System.out.println(name);
    }
}
