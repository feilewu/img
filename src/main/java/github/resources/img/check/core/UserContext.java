package github.resources.img.check.core;

import lombok.Data;

@Data
public class UserContext {

    private String userId;

    private String ip;

    public void clear(){
        this.userId=null;
        this.ip=null;
    }


}
