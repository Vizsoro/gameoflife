package it.challenges.gameoflife.database.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
import it.challenges.gameoflife.database.DatabaseHandler;

@Transactional
public class DatabaseHandlerTest {

	private DatabaseHandler handler;
	private BoardHandler boardHandler;

	@Before
	public void init() {
		Configuration config = new Configuration().configure(new File("src/test/hibernate/hibernate.cfg.xml"));
		handler = new DatabaseHandler(config);
		boardHandler = new BoardHandlerImplementation();
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testFindByCycle() {
		Board board = boardHandler.generateBoard(10, 0.5);
		Map<Integer, Map<Integer, Cell>> originalCells = board.getCells();
		handler.saveCycle(1, board);
		Map<Integer, Map<Integer, Cell>> loadedMap = handler.getCycle(1);
		assertNotNull(loadedMap);
		assertTrue(loadedMap.values().parallelStream().flatMap(map -> map.values().stream())
				.allMatch(c -> originalCells.get(c.getPosX()).get(c.getPosY()).equals(c)));
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testCycleClear() {
		Board board = boardHandler.generateBoard(10, 0.5);
		handler.saveCycle(1, board);
		handler.saveCycle(2, board);
		handler.clearCycles();
		assertNull(handler.getCycle(1));
		assertNull(handler.getCycle(2));
	}

	@Test
	@Transactional
	@Rollback(true)
	public void sizeTest() {
		Board board = boardHandler.generateBoard(10, 0.5);
		handler.saveCycle(1, board);
		handler.saveCycle(2, board);
		Map<Integer, Map<Integer, Cell>> cycle = handler.getCycle(2);
		System.out.println(cycle.size());
		assertTrue(cycle.size() == 10);
		assertTrue(cycle.values().stream().allMatch(map->map.size() == 10));
	}

}
