package github.resources.img.auth.realm;

import github.resources.img.auth.AuthException;
import github.resources.img.auth.subject.Subject;

public interface Realm {

    void auth(Subject subject) throws AuthException;

}
