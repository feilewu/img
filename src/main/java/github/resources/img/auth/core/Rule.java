package github.resources.img.auth.core;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Rule {

    private String pattern;

    private RuleAction action;

}
