package it.challenges.gameoflife.rule;

import it.challenges.gameoflife.board.Cell;

public interface Rule {

	boolean isAvaliable(Cell cell);
	
	void apply(Cell cell);
	
}
