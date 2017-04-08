package it.challenges.gameoflife.rule;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import it.challenges.gameoflife.board.Cell;

@Component
public class RuleFactoryImplementation implements RuleFactory{

	
	private final Set<Rule> allRule;

	
	public RuleFactoryImplementation(){
		allRule = new HashSet<Rule>();
		allRule.add(new RuleDeadToLive());
		allRule.add(new RuleLiveToLive());
		allRule.add(new RuleLiveToDead());
	}
	
	public Set<Rule> findRules(Cell cell) {
		Set<Rule> rules = new HashSet<Rule>();
		for(Rule rule : allRule){
			if(rule.isAvaliable(cell)){
				rules.add(rule);
			}
		}
		return rules;
	}

}
