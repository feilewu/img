package github.resources.img.auth.core;

import github.resources.img.web.entity.User;

import javax.servlet.http.HttpServletRequest;

public interface RuleEngine {

    default void addRule(String pattern,RuleAction ruleAction){}

    default RuleAction match(HttpServletRequest request){
        return RuleAction.PASS;
    }

}
