package github.resources.img.auth.token;

import lombok.Data;

@Data
public class Token {

    /**
     * token string
     */
    private String tokenMsg;

    private String principal;

    /**
     * token创建时间
     */
    private long timestamp;

    /**
     * 有效时间  单位 ms
     */
    private long expireTime;

}
