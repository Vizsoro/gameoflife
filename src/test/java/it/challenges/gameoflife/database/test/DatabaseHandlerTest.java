package it.challenges.gameoflife.database.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.challenges.gameoflife.board.Board;
import it.challenges.gameoflife.board.BoardHandler;
import it.challenges.gameoflife.board.BoardHandlerImplementation;
import it.challenges.gameoflife.database.DatabaseHandler;

public class DatabaseHandlerTest {

	private DatabaseHandler handler = new DatabaseHandler();
	private BoardHandler boardHandler = new BoardHandlerImplementation();
	
	@Test
	public void testSimulationSave(){
		Board board = boardHandler.generateBoard(10, 0.5);
		int simulationId = handler.saveNewSimulation(board);
		assertTrue(handler.saveNewSimulation(board) > 0);
	}
	
}
