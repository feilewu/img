package github.resources.img.check.core;

import javax.servlet.http.HttpServletRequest;

public interface RuleEngine {

    default void addRule(String pattern,RuleAction ruleAction){}

    default RuleAction match(HttpServletRequest request){
        return RuleAction.PASS;
    }

    default boolean isApi(HttpServletRequest request){return false;}

    default void addApiPattern(String pattern){}
}
