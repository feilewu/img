package github.resources.img.auth.core.check;

import lombok.Data;

@Data
public class AuthInfo {

    private String name;

    private String password;

    public String getUserName(){
        return name;
    }

}
