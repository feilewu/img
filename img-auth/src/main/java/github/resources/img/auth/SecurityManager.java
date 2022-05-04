package github.resources.img.auth;

import github.resources.img.auth.subject.Subject;

public interface SecurityManager {

     /**
      * 登录成功返回token
      * @param subject
      * @return
      */
     String login(Subject subject);

     boolean authenticate(String token);

}
