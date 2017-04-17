package it.challenges.gameoflife.rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.challenges.gameoflife.board.Cell;

@Component
public class RuleFactoryImplementation implements RuleFactory{

	@Autowired
	private final List<Rule> allRule = new ArrayList<Rule>();

	
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
