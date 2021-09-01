package com.shenzhijie.loguserinfo.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * All rights Reserved, Designed By www.info4z.club
 * <p>title:com.shenzhijie.loguserinfo.common.utils</p>
 * <p>ClassName:JwtUtils</p>
 * <p>
 * eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLlvLnlvLnloIIiLCJleHAiOjE2MjUwMzgxMTB9.cDbS257gkGg3KHEFNs29kqMwNWbnlqFAw-wiT3zj_1M
 * eyJhbGciOiJIUzI1NiJ9---->base64.encode(header)→
 * eyJzdWIiOiLlvLnlvLnloIIiLCJleHAiOjE2MjUwMzgxMTB9------>载荷,信息用户名,用户id
 * cDbS257gkGg3KHEFNs29kqMwNWbnlqFAw-wiT3zj_1M------->散列加密(payload,盐(secret))
 * TODO(自定义token工具)
 * <p>Description:TODO(返回token的加密解密-->BASE64编码解码----->base64.encode(header) → 载荷,信息用户名,用户id → 散列加密(payload,盐(secret))</p>
 * <p>Compony:Info4z</p>
 * author:zhijieShen
 * date:2021/6/30
 * version:1.0
 * 注意：本内容仅限于公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class JwtUtils {
    public static final String sectet = "sdsadafa";

    public static String createToken(String subject) {
        String token = Jwts.builder().setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60))
                .signWith(SignatureAlgorithm.HS256, sectet)
                .compact();

        return token;
    }

    public static String parseToken(String token) {
        Claims body = Jwts.parser().setSigningKey(sectet).parseClaimsJws(token).getBody();
        String subject = body.getSubject();
        return subject;
    }

    public static void main(String[] args) throws InterruptedException {
        String name = "弹弹堂";
        String token = createToken(name);
        System.out.println("token:      " + token);

        String parseToken = parseToken(token);
        System.out.println("解析出来的token:      " + parseToken);

        TimeUnit.SECONDS.sleep(4);
        parseToken = parseToken(token);
        System.out.println("解析出来的token:      " + parseToken);
    }
}

