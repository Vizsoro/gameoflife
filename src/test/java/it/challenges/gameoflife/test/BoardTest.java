package it.challenges.gameoflife.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Test;

import it.challenges.gameoflife.board.Board;
import it.challenges.gameoflife.board.Cell;
import it.challenges.gameoflife.board.Position;
import it.challenges.gameoflife.pojo.CellColor;
import it.challenges.gameoflife.pojo.CellState;

public class BoardTest {
	
	
	@Test(expected=IllegalArgumentException.class)
	public void initErrorTest(){
		Map<Position,Cell> cells = new HashMap<>();
		Cell cell = new Cell();
		cells.put(new Position(0,0), cell);
		cells.put(new Position(0,1), cell);
		new Board(cells);		
	}
	
	@Test
	public void sizeTest(){
		Map<Position,Cell> cells = new HashMap<>();
		Cell cell = new Cell();
		cells.put(new Position(0,0), cell);
		cells.put(new Position(0,1), cell);
		cells.put(new Position(1,1), cell);
		cells.put(new Position(1,0), cell);
		Board board = new Board(cells);
		Map<Entry<Position, Cell>, Long> collect = board.getCells().entrySet().stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		assertTrue(board.getBoardSize() == 2);
	}
	
	@Test
	public void equalsTestForCopy(){
		Position zeroPos = new Position(0,0);
		Map<Position,Cell> cells = new HashMap<>();
		cells.put(zeroPos, new Cell().setColor(CellColor.BLUE).setState(CellState.LIVE));
		Board board = new Board(cells);
		Board board2 = board.copy();
		assertFalse(board.equals(board2));
	}
	
	@Test
	public void cellTestForCopy(){
		Position zeroPos = new Position(0,0);
		Map<Position,Cell> cells = new HashMap<>();
		cells.put(zeroPos, new Cell().setColor(CellColor.BLUE).setState(CellState.LIVE));
		Board board = new Board(cells);
		Board board2 = board.copy();
		assertTrue(board2.getCells().get(zeroPos).getColor().equals(CellColor.BLUE));
		assertTrue(board2.getCells().get(zeroPos).getState().equals(CellState.LIVE));
	}
	
	@Test
	public void cellModificationTestForCopy(){
		Position zeroPos = new Position(0,0);
		Cell cell1= new Cell().setColor(CellColor.BLUE).setState(CellState.LIVE); 
		Map<Position,Cell> cells = new HashMap<>();
		cells.put(zeroPos, cell1);
		Board board = new Board(cells);
		Board board2 = board.copy();
		cell1.setColor(CellColor.GREEN).setState(CellState.DEAD);
		assertTrue(board2.getCells().get(zeroPos).getColor().equals(CellColor.BLUE));
		assertTrue(board2.getCells().get(zeroPos).getState().equals(CellState.LIVE));
	}

}
