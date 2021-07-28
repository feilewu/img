package github.resources.img.auth.core.check;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthInfo {

    private String id;

    private String name;

    private String password;

    public String getUserId(){
        return id;
    }

}
