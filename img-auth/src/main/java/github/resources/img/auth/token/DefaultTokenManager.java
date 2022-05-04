package github.resources.img.auth.token;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONUtil;
import github.resources.img.auth.AuthException;
import lombok.Data;

import java.util.Base64;
import java.util.Date;

@Data
public class DefaultTokenManager implements TokenManager{

    private String salt = "img!";

    /**
     * 过期时间 单位毫秒
     */
    private long defaultExpireTime = 30 * 60 * 1000L;

    public DefaultTokenManager(){

    }

    public String generateToken( String principal){
        return generateToken(principal,null);
    }

    @Override
    public String generateToken(String principal, Long expireTime) {
        if(expireTime==null||expireTime<0){
            expireTime = defaultExpireTime;
        }
        Token token = new Token();
        token.setPrincipal(principal);
        token.setTimestamp(new Date().getTime());
        token.setExpireTime(expireTime);
        String jsonStr = JSONUtil.toJsonStr(token);
        String base64 = Base64.getEncoder().encodeToString(jsonStr.getBytes());
        return base64+"_"+getSignature(base64);
    }

    public String getSignature(String tokenJsonStr){
        return DigestUtil.md5Hex((tokenJsonStr + salt).getBytes());
    }

    public Token checkToken(String tokenStr) throws AuthException {
        if(CharSequenceUtil.hasEmpty(tokenStr)){
            throw new AuthException("token is empty");
        }
        String [] tokenItems = tokenStr.split("_");
        if(tokenItems.length!=2){
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
        long expireTime = token.getExpireTime();
        if((nowTimestamp-timestamp)> expireTime){
            throw new AuthException("token expired");
        }
        return token;
    }

}
