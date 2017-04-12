package it.challenges.gameoflife.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {

	private List<List<Cell>> cells;
	private int boardSize;
	
	public Board(List<List<Cell>> cells){
		checkCells(cells);
		this.cells = cells;
		this.boardSize = cells.size();
	}
	
	private void checkCells(List<List<Cell>> cells){
		int rowNum = cells.size();
		for (List<Cell> row : cells){
			if(row.size() != rowNum){
				throw new IllegalArgumentException();
			}
		}
		
	}

	public List<List<Cell>> getCells() {
		return Collections.unmodifiableList(cells);
	}

	public int getBoardSize() {
		return boardSize;
	}
	
	public Board copy(){
		List<List<Cell>> copyBoard = new ArrayList<List<Cell>>();
		for (List<Cell> row : cells) {
			List<Cell> newRow = new ArrayList<Cell>();
			for (Cell cell : row) {
				newRow.add(new Cell(cell));
			}
			copyBoard.add(newRow);
		}
		return new Board(copyBoard);
		
	}
	
	
	
}
