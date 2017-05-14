package it.challenges.gameoflife.database.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import java.io.File;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import it.challenges.gameoflife.board.Board;
import it.challenges.gameoflife.board.BoardHandler;
import it.challenges.gameoflife.board.BoardHandlerImplementation;
import it.challenges.gameoflife.board.Cell;
import it.challenges.gameoflife.board.Position;
import it.challenges.gameoflife.database.DatabaseHandler;

@Transactional
public class DatabaseHandlerTest {

	private DatabaseHandler handler;
	private BoardHandler boardHandler;
	
	@Before
	public void init(){
		Configuration config = new Configuration().configure(new File("src/test/hibernate/hibernate.cfg.xml"));
		handler = new DatabaseHandler(config);
		boardHandler = new BoardHandlerImplementation();
	}
	
	
	@Test
	@Transactional
	@Rollback(true)
	public void testCycleSave(){
		Board board = boardHandler.generateBoard(10, 0.5);
		long id = handler.saveCycle(4, board);
		assertTrue(id > -1);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testFindByCycle(){
		Board board = boardHandler.generateBoard(10, 0.5);
		Map<Position, Cell> originalCells = board.getCells();
		handler.saveCycle(4, board);
		Map<Position, Cell> loadedMap = handler.getCycle(4);
		assertNotNull(loadedMap);
		assertTrue(loadedMap.values().parallelStream().allMatch(c->originalCells.get(c.getPosition()).equals(c)));
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testCycleClear(){
		Board board = boardHandler.generateBoard(10, 0.5);
		Map<Position, Cell> originalCells = board.getCells();
		handler.saveCycle(1, board);
		handler.saveCycle(2, board);
		handler.clearCycles();
		assertNull(handler.getCycle(4));
		assertNull(handler.getCycle(3));
	}
	
	
	@Test
	@Transactional
	@Rollback(true)
	public void sizeTest(){
		Board board = boardHandler.generateBoard(10, 0.5);
		handler.saveCycle(1, board);
		handler.saveCycle(2, board);
		handler.saveCycle(3, board);
		Map<Position, Cell> cycle = handler.getCycle(2);
		System.out.println(cycle.size());
		assertTrue(cycle.size()==100);
	}
	
	
}
