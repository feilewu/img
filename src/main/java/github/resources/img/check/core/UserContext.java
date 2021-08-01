package github.resources.img.check.core;

import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class UserContext {

    private String userId;

    private String ip;

    public void clear(){
        this.userId=null;
        this.ip=null;
    }

    public String getOwner(){
        if(StringUtils.hasText(userId)){
            return userId;
        }else {
            return ip;
        }
    }


}
