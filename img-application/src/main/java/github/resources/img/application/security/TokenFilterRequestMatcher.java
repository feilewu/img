package github.resources.img.application.security;

import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author pfxuchn@gmail.com
 * @date 2022/10/7 12:25
 **/
public class TokenFilterRequestMatcher implements RequestMatcher {

    private final List<AntPathRequestMatcher> pass = new ArrayList<>();

    private AntPathRequestMatcher antPathRequestMatcher ;

    public TokenFilterRequestMatcher(String antUrl) {
        this.antPathRequestMatcher = new AntPathRequestMatcher(antUrl);
    }


    @Override
    public boolean matches(HttpServletRequest request) {
        return pass.stream().noneMatch(matcher -> matcher.matches(request)) && antPathRequestMatcher.matches(request);
    }

    public void setPass(String ...antUrls) {
        Arrays.stream(antUrls).forEach(antUrl->{
            pass.add(new AntPathRequestMatcher(antUrl));
        });
    }

    public void setAntPathRequestMatcher(AntPathRequestMatcher antPathRequestMatcher) {
        this.antPathRequestMatcher = antPathRequestMatcher;
    }
}
