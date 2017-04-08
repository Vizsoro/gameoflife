package it.challenges.gameoflife.board;

import java.util.List;

import org.springframework.stereotype.Component;


public interface BoardHandler {
	
	public List<List<Cell>> getPreviousBoard();

	void setNeighbourInfo(Cell cell);
	
	public List<Cell> getNeighbours(Position position);
	
	List<List<Cell>> generateBoard(int size, double probability);
	
	void prepareForNextCycle();
	
	List<List<Cell>> getBoard();
	
	public NeighbourInfo findNeighbourInfo(Position position);
}