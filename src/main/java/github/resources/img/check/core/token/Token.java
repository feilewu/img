package github.resources.img.check.core.token;

import lombok.Data;

@Data
public class Token {

    private String userId;

    /**
     * token创建时间
     */
    private long timestamp;

    /**
     * 有效时间  单位 ms
     */
    private long expireTime;

}
