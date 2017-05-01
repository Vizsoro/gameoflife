package it.challenges.gameoflife.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import it.challenges.gameoflife.board.Board;
import it.challenges.gameoflife.board.BoardHandler;
import it.challenges.gameoflife.board.BoardHandlerImplementation;
import it.challenges.gameoflife.board.Cell;
import it.challenges.gameoflife.board.NeighbourInfo;
import it.challenges.gameoflife.board.Position;
import it.challenges.gameoflife.pojo.CellColor;
import it.challenges.gameoflife.pojo.CellState;

public class BoardHandlerTest {

	private BoardHandler boardHandler = new BoardHandlerImplementation();

	@Test
	public void neighbourTest() {
		boardHandler.saveBoard(boardHandler.generateBoard(4, 0.5));
		List<Cell> neighbours = boardHandler.getNeighbours(new Position(0, 0));
		Position neighbour1Pos = new Position(0, 1);
		Position neighbour2Pos = new Position(1, 3);
		boolean neighbour1 = neighbours.stream().anyMatch(c -> c.equals(neighbour1Pos));
		boolean neighbour2 = neighbours.stream().anyMatch(c -> c.equals(neighbour2Pos));
		assertTrue(neighbour1);
		assertTrue(neighbour2);
	}

	@Test
	public void neighbourInfoTestOneCell() {
		Cell cell = new Cell();
		cell.setPosition(new Position(0, 0));
		cell.setColor(CellColor.BLUE);
		cell.setState(CellState.DEAD);
		Map<Position, Cell> cells = new HashMap<>();
		cells.put(new Position(0, 0), cell);
		boardHandler.saveBoard(new Board(cells));
		NeighbourInfo info = boardHandler.findNeighbourInfo(new Position(0, 0));
		assertTrue(info.getColor().equals(CellColor.GREEN));
		assertTrue(info.getLivingNeighbour() == 0);
	}

	@Test
	public void zeroProbabilityTest() {
		assertTrue(boardHandler.generateBoard(10, 0).getCells().values().parallelStream()
				.allMatch(c -> CellState.LIVE.equals(c.getState())));
	}
	
	@Test
	public void oneProbabilityTest() {
		assertTrue(boardHandler.generateBoard(10, 1).getCells().values().parallelStream()
				.allMatch(c -> CellState.DEAD.equals(c.getState())));
	}
}
