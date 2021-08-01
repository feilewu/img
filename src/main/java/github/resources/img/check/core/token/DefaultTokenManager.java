package github.resources.img.check.core.token;

import cn.hutool.json.JSONUtil;
import github.resources.img.check.core.exception.AuthException;
import lombok.Data;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.Base64;
import java.util.Date;

@Data
public class DefaultTokenManager implements TokenManager{

    private String salt = "img!";

    /**
     * 过期时间 单位秒
     */
    private long expireTime= 30 * 60L;

    public DefaultTokenManager(){

    }

    public String generateToken( String userId){
        Token token = new Token();
        token.setUserId(userId);
        token.setTimestamp(new Date().getTime());
        String jsonStr = JSONUtil.toJsonStr(token);
        String base64 = Base64.getEncoder().encodeToString(jsonStr.getBytes());
        return base64+"_"+getSignature(base64);
    }

    public String getSignature(String tokenJsonStr){
        return DigestUtils.md5DigestAsHex((tokenJsonStr + salt).getBytes());
    }

    public Token checkToken(String tokenStr, Long expireTime) throws AuthException {
        if(!StringUtils.hasText(tokenStr)){
            throw new AuthException("token is empty");
        }
        String [] tokenItems = null;
        if((tokenItems=tokenStr.split("_")).length!=2){
            throw new AuthException("token format error");
        }
        boolean equals = getSignature(tokenItems[0]).equals(tokenItems[1]);
        if(!equals){
            throw new AuthException("token illegal");
        }
        byte[] decode = Base64.getDecoder().decode(tokenItems[0].getBytes());
        String tokenJson = new String(decode);
        Token token = JSONUtil.toBean(tokenJson, Token.class);
        long timestamp = token.getTimestamp();
        long nowTimestamp = new Date().getTime();
        if((nowTimestamp-timestamp)/1000> (expireTime==null?this.expireTime:expireTime)){
            throw new AuthException("token expired");
        }
        return token;
    }

}
