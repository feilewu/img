package github.resources.img.application.configuration;

import lombok.Data;

@Data
public class UserContext {

    private boolean guest;

    private String userId;

    private String ip;

    public void clear(){
        this.guest=false;
        this.userId=null;
        this.ip=null;
    }
}
