package it.challenges.gameoflife.board;

import java.util.List;


public interface BoardHandler {
	
	List<List<Cell>> getPreviousBoard();
	
	void saveBoard(List<List<Cell>> board);

	void setNeighbourInfo(Cell cell);
	
	List<Cell> getNeighbours(Position position);
	
	List<List<Cell>> generateBoard(int size, double probability);

	void savePreviousBoard(List<List<Cell>> board);
	
	List<List<Cell>> getBoard();
	
	NeighbourInfo findNeighbourInfo(Position position);
}