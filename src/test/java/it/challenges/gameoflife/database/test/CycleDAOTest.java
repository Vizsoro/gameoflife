package it.challenges.gameoflife.database.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Map;
import java.util.Optional;

import javax.swing.text.Position;
import javax.transaction.Transactional;

import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import it.challenges.gameoflife.board.BoardHandler;
import it.challenges.gameoflife.board.BoardHandlerImplementation;
import it.challenges.gameoflife.board.Cell;
import it.challenges.gameoflife.database.CellEntity;
import it.challenges.gameoflife.database.CycleDAO;
import it.challenges.gameoflife.database.CycleEntity;

public class CycleDAOTest {

	private CycleDAO cycleDAO;
	private BoardHandler boardHandler;

	@Before
	public void init() {
		Configuration config = new Configuration().configure(new File("src/test/hibernate/hibernate.cfg.xml"));
		cycleDAO = new CycleDAO(config.buildSessionFactory());
		boardHandler = new BoardHandlerImplementation();
	}

	@Test
	@Transactional
	@Rollback(true)
	public void saveCycleTest() {
		CycleEntity entity = new CycleEntity();
		entity.setCellEntities(boardHandler.generateBoard(5, 0.5).getCells());
		entity.setCycle(15);
		assertTrue(cycleDAO.save(entity) > 0);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void findByCycleIntTest() {
		CycleEntity entity = new CycleEntity();
		Map<Integer,Map<Integer,Cell>> cells = boardHandler.generateBoard(5, 0.5).getCells();
		entity.setCellEntities(cells);
		entity.setCycle(15);
		cycleDAO.save(entity);
		CycleEntity entity2 = cycleDAO.findByCycle(15, false);
		assertNotNull(entity2);
		assertTrue(entity2.equals(entity));
		Optional<CellEntity> savedCell = entity2.getCellEntities().stream()
				.filter(e -> e.getPositionX() == 0 && e.getPositionY() == 0).findFirst();
		assertTrue(savedCell.isPresent());
		assertTrue(savedCell.get().equals(new CellEntity(cells.get(0).get(0))));
	}
	
	@Test
	public void notFoundTest() {
		assertNull(cycleDAO.findByCycle(15, true));
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void findByCycleSizeTest(){
		CycleEntity entity = new CycleEntity();
		Map<Integer,Map<Integer,Cell>> cells = boardHandler.generateBoard(100, 0.5).getCells();
		entity.setCellEntities(cells);
		entity.setCycle(15);
		cycleDAO.save(entity);
		CycleEntity entity2 = cycleDAO.findByCycle(15, false);
		assertTrue(entity2.getCellEntities().size() == 10000);
	}

}
