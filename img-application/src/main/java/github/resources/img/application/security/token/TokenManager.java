package github.resources.img.application.security.token;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.json.JSONUtil;
import github.resources.img.application.utils.ResponseUtil;
import github.resources.img.core.ResponseStatus;
import github.resources.img.core.model.dto.Response;
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

    public Response checkToken(String token) {

        if(CharSequenceUtil.hasEmpty(token)){
            return ResponseUtil.fail(String.valueOf(ResponseStatus.TOKEN_ILLEGAL.getCode()),
                    ResponseStatus.TOKEN_ILLEGAL.getMessage());
        }
        String [] tokenItems = token.split("_");
        if(tokenItems.length!=2){
            return ResponseUtil.fail(String.valueOf(ResponseStatus.TOKEN_ILLEGAL.getCode()),
                    ResponseStatus.TOKEN_ILLEGAL.getMessage());
        }
        boolean equals = getSignature(tokenItems[0]).equals(tokenItems[1]);
        if(!equals){
            return ResponseUtil.fail(String.valueOf(ResponseStatus.TOKEN_ILLEGAL.getCode()),
                    ResponseStatus.TOKEN_ILLEGAL.getMessage());
        }
        byte[] decode = Base64.getDecoder().decode(tokenItems[0].getBytes());
        String tokenJson = new String(decode);
        Token tokenBean = JSONUtil.toBean(tokenJson, Token.class);
        long timestamp = tokenBean.getTimestamp();
        long nowTimestamp = new Date().getTime();
        long expireTime = tokenBean.getExpireTime();
        if((nowTimestamp-timestamp)> expireTime){
            return ResponseUtil.fail(String.valueOf(ResponseStatus.TOKEN_EXPIRE.getCode()),
                    ResponseStatus.TOKEN_EXPIRE.getMessage());
        }
        return ResponseUtil.ok(tokenBean.getId());
    }


}
