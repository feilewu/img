package github.resources.img.auth.core;

public enum RuleAction {

    INTERCEPT("intercept"),
    PASS("pass");

    private final String str;

    RuleAction(String str){
        this.str=str;
    }

    public static RuleAction getAction(String str){
        for(RuleAction ruleAction:RuleAction.values()){
            if(ruleAction.str.equals(str)){
                return ruleAction;
            }
        }
        return null;
    }

}
