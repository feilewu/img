package github.resources.img.application.security.token;

import lombok.Data;

/**
 * @author pfxuchn@gmail.com
 * @date 2022/9/28 21:46
 **/
@Data
public class Token {

    private String id;

    /**
     * token创建时间
     */
    private long timestamp;

    /**
     * 有效时间  单位 ms
     */
    private long expireTime;




    public String toString(){
        return null;
    }

}

