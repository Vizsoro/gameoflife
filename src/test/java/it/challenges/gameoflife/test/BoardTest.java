package it.challenges.gameoflife.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.challenges.gameoflife.board.Board;
import it.challenges.gameoflife.board.Cell;
import it.challenges.gameoflife.pojo.CellColor;
import it.challenges.gameoflife.pojo.CellState;

public class BoardTest {
	
	
	@Test(expected=IllegalArgumentException.class)
	public void initErrorTest(){
		List<List<Cell>> cells = new ArrayList<List<Cell>>();
		List<Cell> row = new ArrayList<>();
		row.add(new Cell());
		cells.add(row);
		cells.add(row);
		new Board(cells);		
	}
	
	@Test
	public void sizeTest(){
		List<List<Cell>> cells = new ArrayList<List<Cell>>();
		List<Cell> row = new ArrayList<>();
		row.add(new Cell());
		row.add(new Cell());
		cells.add(row);
		cells.add(row);
		Board board = new Board(cells);
		assertTrue(board.getBoardSize() == 2);
	}
	
	@Test
	public void equalsTestForCopy(){
		List<List<Cell>> cells = new ArrayList<List<Cell>>();
		List<Cell> row = new ArrayList<>();
		row.add(new Cell());
		cells.add(row);
		Board board = new Board(cells);
		Board board2 = board.copy();
		assertFalse(board.equals(board2));
	}
	
	@Test
	public void cellTestForCopy(){
		List<List<Cell>> cells = new ArrayList<List<Cell>>();
		List<Cell> row = new ArrayList<>();
		row.add(new Cell().setColor(CellColor.BLUE).setState(CellState.LIVE));
		cells.add(row);
		Board board = new Board(cells);
		Board board2 = board.copy();
		assertTrue(board2.getCells().get(0).get(0).getColor().equals(CellColor.BLUE));
		assertTrue(board2.getCells().get(0).get(0).getState().equals(CellState.LIVE));
	}
	
	@Test
	public void cellModificationTestForCopy(){
		List<List<Cell>> cells = new ArrayList<List<Cell>>();
		List<Cell> row = new ArrayList<>();
		Cell cell1= new Cell().setColor(CellColor.BLUE).setState(CellState.LIVE); 
		row.add(cell1);
		cells.add(row);
		Board board = new Board(cells);
		Board board2 = board.copy();
		cell1.setColor(CellColor.GREEN).setState(CellState.DEAD);
		assertTrue(board2.getCells().get(0).get(0).getColor().equals(CellColor.BLUE));
		assertTrue(board2.getCells().get(0).get(0).getState().equals(CellState.LIVE));
	}

}
