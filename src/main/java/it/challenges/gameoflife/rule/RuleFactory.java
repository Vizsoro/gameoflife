package it.challenges.gameoflife.rule;

import java.util.Set;

import it.challenges.gameoflife.board.Cell;

public interface RuleFactory {

	Set<Rule> findRules(Cell cell);
}
