package github.resources.img.auth.core.check;

import github.resources.img.auth.core.Account;

public interface Realm {

    AuthInfo auth(Account account);

}
