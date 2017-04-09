package it.challenges.gameoflife.cycle;

import java.util.List;

import it.challenges.gameoflife.board.Cell;

public interface CycleManagerInterface {

	void startGame (int size, double probability);
	
	void moveToNextCycle();
	
	void moveToPreviousCycle();
	
	List<List<Cell>> getCurrentState();

}