package github.resources.img.auth.core;

import github.resources.img.web.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DefaultRuleEngine implements RuleEngine{

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    private final List<Rule> rules;

    public DefaultRuleEngine(){
        rules = new ArrayList<>();
    }

    @Override
    public void addRule(String pattern, RuleAction ruleAction) {
        rules.add(new Rule(pattern,ruleAction));
    }

    @Override
    public RuleAction match(HttpServletRequest request){
        log.debug("method=[{}], url=[{}],",request.getMethod(),request.getRequestURI());
        for(Rule rule : rules){
            boolean match = isMatch(rule.getPattern(), request.getRequestURI());
            if(match){
                return rule.getAction();
            }
        }
        return RuleAction.PASS;
    }

    private boolean isMatch(String pattern,String url){
        return antPathMatcher.match(pattern,url);
    }

}
