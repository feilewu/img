package github.resources.img.check.core;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Rule {

    private String pattern;

    private RuleAction action;

}
