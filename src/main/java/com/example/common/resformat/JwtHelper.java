package com.example.common.resformat;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.common.login.entity.LoginUser;
import org.apache.commons.codec.binary.Base64;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
//添加构成JWT的参数 头部+载荷+签名。
//头部（Header）{"typ": "JWT","alg": "HS256"}
/*载荷（Payload）
{ "iss": "Online JWT Builder", //iss(Issuser)：代表这个JWT的签发主体；
  "iat": 1416797419, //iat(Issued at)：是一个时间戳，代表这个JWT的签发时间；
  "exp": 1448333419, //exp(Expiration time)：是一个时间戳，代表这个JWT的过期时间；
  "aud": "www.example.com", //aud(Audience)：代表这个JWT的接收对象；
  "sub": "jrocket@example.com", //sub(Subject)：代表这个JWT的主体，即它的所有人；
  "GivenName": "Johnny",
  "Surname": "Rocket",
  "Email": "jrocket@example.com",
  "Role": [ "Manager", "Project Administrator" ]
}*/
//签名（Signature）
public class JwtHelper {
    public static final String secret = "22640E66C97339C027F77759025CAD17";

    /**
     * 由字符串生成加密key
     *
     * @param secret 当前用户的secret（MD5字符串）
     * @return
     */
    public static SecretKey generalKey(String secret) {
        byte[] encodedKey = Base64.decodeBase64(secret);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 解密jwt
     */
    public static Claims parseJWT(String jsonWebToken) {
        SecretKey key = generalKey(secret);
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jsonWebToken)
                .getBody();
        return claims;
    }
    //判断是否到期
    public static boolean isExpiration(String token){
        return parseJWT(token).getExpiration().before(new Date());
    }
    /**
     * 创建jwt
     * @param uid       用户id
     * @param subject   用户信息转成的json
     * @param TTLMillis 过期时间毫秒
     * @return
     */
    public static String createJWT(String uid, String subject, long TTLMillis, String secret) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        // 生成签名密钥
        Key signingKey = generalKey(secret);
        JwtBuilder builder = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .claim("userid", uid)
                .setIssuedAt(now)//iat(issued at): 在什么时候签发的(UNIX时间)，是否使用是可选的；
                .setSubject(subject)//sub: 该JWT所面向的用户，是否使用是可选的；
                //.setIssuer(issuer)//iss: 该JWT的签发者，是否使用是可选的；
                //.setAudience(audience)//aud: 接收该JWT的一方，是否使用是可选的；
                .signWith(signatureAlgorithm, signingKey);//
        // 添加Token过期时间
        if (TTLMillis >= 0) {
            long expMillis = nowMillis + TTLMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);//exp(expires): 什么时候过期，这里是一个Unix时间戳，是否使用是可选的；
            //.setNotBefore(now);//nbf (Not Before)：如果当前时间在nbf里的时间之前，则Token不被接受；一般都会留一些余地，比如几分钟；，是否使用是可选的；
        }

        // 生成JWT
        return builder.compact();
    }

    /**
     * 生成subject信息
     * @param user
     */
    public static String generalSubject(LoginUser user) {
        return JSONObject.toJSONString(user);
    }

    /**
     * 创建token:通过登录后的用户，生产token,有效时间60分钟
     */
    public static String createJWT(LoginUser User) {
        String subject = JwtHelper.generalSubject(User);
        String accessToken = JwtHelper.createJWT(User.getUser().getId(), subject, 60 * 60 * 1000, secret);
        return accessToken;
    }
    public static LoginUser getUserByToken(String token) {
        String subject = JwtHelper.parseJWT(token).getSubject();
        return JSON.parseObject(subject,LoginUser.class);
    }


}
