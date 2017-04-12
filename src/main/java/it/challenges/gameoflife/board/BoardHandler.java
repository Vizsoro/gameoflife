package it.challenges.gameoflife.board;

import java.util.List;


public interface BoardHandler {
	
	Board getPreviousBoard();
	
	void saveBoard(Board board);

	void setNeighbourInfo(Cell cell);
	
	List<Cell> getNeighbours(Position position);
	
	Board generateBoard(int size, double probability);

	void savePreviousBoard(Board board);
	
	Board getBoard();
	
	NeighbourInfo findNeighbourInfo(Position position);
}