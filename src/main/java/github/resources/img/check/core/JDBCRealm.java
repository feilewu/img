package github.resources.img.check.core;

public class JDBCRealm implements Realm{
    @Override
    public AuthInfo auth(Account account) {
        if(account!=null&&"fly".equals(account.getName())&&"123".equals(account.getPassword())){
            return new AuthInfo("1",account.getName(),account.getPassword());
        }
        return null;

    }
}
