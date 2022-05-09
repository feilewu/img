package github.resources.img.application.auth;

import github.resources.img.auth.AuthException;
import github.resources.img.auth.realm.Realm;
import github.resources.img.auth.subject.Subject;
import org.springframework.stereotype.Component;

@Component
public class JDBCRealm implements Realm {


    @Override
    public void auth(Subject subject) throws AuthException {

    }
}
