package com.example.preSpring.tools;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 实现token验证登录
 * @author 小叶子
 */
@Component
public class TokenUtil {
    @Resource
    private RedisUtil redisUtil;

    private static final long EXPIRE_TIME = 15 * 60 * 1000;

    private static final String TOKEN_SECRET = "wejkdaskhfkasjfweiisjf";

    public String sign(String userId, String password) {
        try {
            // 设置过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            // 私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("Type", "Jwt");
            header.put("alg", "HS256");
            String theToken =  JWT.create()
                    .withHeader(header)
                    .withClaim("userId", userId)
                    .withClaim("password", password)
                    .withExpiresAt(date)
                    .sign(algorithm);
            redisUtil.set(theToken,userId,12000);
            System.out.println("this is find:" + redisUtil.get(theToken));
            System.out.println("this is key:" + theToken);
            return theToken;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Resource
    private RedisUtil redis;

    public boolean verify(String token){
        if(redis.get(token) != null){
            String userId = (String) redis.get(token);
            redis.set(token,userId,12000);
            return true;
        }
        return false;
//        try {
//            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
//            JWTVerifier verifier = JWT.require(algorithm).build();
//            DecodedJWT jwt = verifier.verify(token);
//            return true;
//        } catch (Exception e){
//            return false;
//        }
    }

}
