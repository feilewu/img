package github.resources.img.application.security.token;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

import static com.sun.org.apache.bcel.internal.classfile.Utility.getSignature;

/**
 * @author pfxuchn@gmail.com
 * @date 2022/9/28 21:49
 **/
@Component
public class TokenManager {
    /**
     * 过期时间 单位毫秒
     */
    private static final long DEFAULT_EXPIRETIME = 30 * 60 * 1000L;

    public String generateToken(String id) {
        return generateToken(id, DEFAULT_EXPIRETIME);
    }

    public String generateToken(String id, Long expireTime) {

        if(expireTime==null||expireTime<0){
            expireTime = DEFAULT_EXPIRETIME;
        }
        Token token = new Token();
        token.setId(id);
        token.setTimestamp(new Date().getTime());
        token.setExpireTime(expireTime);
        String jsonStr = JSONUtil.toJsonStr(token);
        String base64 = Base64.getEncoder().encodeToString(jsonStr.getBytes());
        return base64+"_"+getSignature(base64);
    }

    public String checkToken(String token) {
        if(CharSequenceUtil.hasEmpty(token)){
            throw new IllegalArgumentException("token is empty");
        }
        String [] tokenItems = token.split("_");
        if(tokenItems.length!=2){
            throw new IllegalArgumentException("token format error");
        }
        boolean equals = getSignature(tokenItems[0]).equals(tokenItems[1]);
        if(!equals){
            throw new IllegalArgumentException("token illegal");
        }
        byte[] decode = Base64.getDecoder().decode(tokenItems[0].getBytes());
        String tokenJson = new String(decode);
        Token tokenBean = JSONUtil.toBean(tokenJson, Token.class);
        long timestamp = tokenBean.getTimestamp();
        long nowTimestamp = new Date().getTime();
        long expireTime = tokenBean.getExpireTime();
        if((nowTimestamp-timestamp)> expireTime){
            throw new IllegalArgumentException("token expired");
        }
        return token;
    }


}
