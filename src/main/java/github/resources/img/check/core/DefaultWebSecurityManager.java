package github.resources.img.check.core;

import cn.hutool.json.JSONUtil;
import github.resources.img.check.core.config.CheckConfig;
import github.resources.img.check.core.exception.AuthException;
import github.resources.img.check.core.token.Token;
import github.resources.img.check.core.token.TokenManager;
import github.resources.img.check.core.utils.WebUtil;
import github.resources.img.util.ResponseUtil;
import github.resources.img.web.exception.UserFriendlyRuntimeException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Data
@Slf4j
public class DefaultWebSecurityManager implements WebSecurityManager{

    private RuleEngine ruleEngine;

    private Realm realm;

    private TokenManager tokenManager;

    public DefaultWebSecurityManager(){
    }

    @Override
    public AuthInfo login(Account account) throws AuthException {
        //校验通过
        AuthInfo authInfo = realm.auth(account);
        if(authInfo==null){
            throw new AuthException("error name or password");
        }
        return authInfo;
    }

    @Override
    public boolean authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserContext userContext = ContextHolder.getInstance().getContext();
        if(userContext==null){
            throw new UserFriendlyRuntimeException("failed to get userContext");
        }
        userContext.clear();
        String ipAddress = WebUtil.getIpAddress(request);
        userContext.setIp(ipAddress);
        if(RuleAction.PASS.equals(ruleEngine.match(request))){
            return true;
        }else {
            if(checkToken(request)){
                return true;
            }
            if(ruleEngine.isApi(request)){
                response.setHeader("Content-Type","application/json");
                response.getWriter().print(JSONUtil.toJsonStr(ResponseUtil.fail("Unauthenticated user")));
            }else {
                response.sendRedirect(CheckConfig.loginUrl);
            }
            return false;
        }
    }

    @Override
    public String generateToken(String userId) {
        return tokenManager.generateToken(userId);
    }

    @Override
    public String generateToken(String userId, Long expireTime) {
        return tokenManager.generateToken(userId,expireTime);
    }

    protected boolean checkToken(HttpServletRequest request){
        String tokenStr = WebUtil.getToken(request);
        Token token;
        try {
            token = tokenManager.checkToken(tokenStr);
        } catch (AuthException e) {
            log.info("token=[{}]Unverified",tokenStr);
            return false;
        }
        UserContext userContext = ContextHolder.getInstance().getContext();
        userContext.setUserId(token.getUserId());
        return true;
    }



}
