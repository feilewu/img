//package github.resources.img.web.shiro;
//
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.realm.AuthenticatingRealm;
//
//public class UserRealm extends AuthenticatingRealm {
//
//
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        //从token获取身份信息，token代表用户输入的信息
//        String name = (String)token.getPrincipal();
//        //模拟从数据库中取密码
//        String password = "123";
//        if("admin".equals(name) && password.equals(String.valueOf((char[]) token.getCredentials()))){
//            return new SimpleAuthenticationInfo(name, password, this.getName());
//        }
//        return null;
//    }
//}
