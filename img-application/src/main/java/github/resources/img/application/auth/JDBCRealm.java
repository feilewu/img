package github.resources.img.application.auth;

import github.resources.img.application.service.UserService;
import github.resources.img.auth.AuthException;
import github.resources.img.auth.realm.Realm;
import github.resources.img.auth.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JDBCRealm implements Realm {

    @Autowired
    private UserService userService;

    @Override
    public void auth(Subject subject) throws AuthException {
        userService.authUser(subject.getPrincipal().name(),subject.getCertificate().body());
    }
}
