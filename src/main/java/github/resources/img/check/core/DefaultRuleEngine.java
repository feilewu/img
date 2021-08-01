package github.resources.img.check.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DefaultRuleEngine implements RuleEngine{

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    private final List<Rule> rules;

    private final List<String> apiPatterns;

    public DefaultRuleEngine(){
        rules = new ArrayList<>();
        apiPatterns = new ArrayList<>();
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

    @Override
    public boolean isApi(HttpServletRequest request) {
        for(String pattern:apiPatterns){
            if(isMatch(pattern,request.getRequestURI())){
                return true;
            }
        }
        return false;
    }

    @Override
    public void addApiPattern(String pattern) {
        apiPatterns.add(pattern);
    }

    private boolean isMatch(String pattern,String url){
        return antPathMatcher.match(pattern,url);
    }

}
