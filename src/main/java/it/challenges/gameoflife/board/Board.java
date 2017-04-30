package it.challenges.gameoflife.board;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.OptionalInt;

public class Board {

	private Map<Position,Cell> cells;
	private int boardSize;
	
	public Board(Map<Position,Cell> cells){	
		checkCells(cells);
		this.cells = cells;
		this.boardSize = (int) Math.sqrt(cells.size());
	}
	
	private void checkCells(Map<Position,Cell> cells){
		OptionalInt maxX = cells.keySet().stream().mapToInt(Position::getX).max();
		OptionalInt maxY = cells.keySet().stream().mapToInt(Position::getY).max();
		if( !maxX.isPresent() || !maxY.isPresent() || maxY.getAsInt() != maxX.getAsInt()){
			throw new IllegalArgumentException();
		}
		
		
	}
	public Map<Position,Cell> getCells() {
		return Collections.unmodifiableMap(cells);
	}

	public int getBoardSize() {
		return boardSize;
	}
	
	public Board copy(){
		Map<Position,Cell> copyCells = new HashMap<>();
		for (Entry<Position, Cell> entry : cells.entrySet()) {
			copyCells.put(entry.getKey(), new Cell(entry.getValue()));
		}
		return new Board(copyCells);
		
	}
	
	
	
}
