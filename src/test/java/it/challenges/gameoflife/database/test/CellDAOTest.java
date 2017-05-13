package it.challenges.gameoflife.database.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.io.File;

import javax.transaction.Transactional;

import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import it.challenges.gameoflife.database.CellDAO;
import it.challenges.gameoflife.database.CellEntity;
import it.challenges.gameoflife.pojo.CellColor;
import it.challenges.gameoflife.pojo.CellState;

@Transactional
public class CellDAOTest {

	private CellDAO cellDAO;

	@Before
	public void init(){
		Configuration config = new Configuration().configure(new File("src/test/hibernate/hibernate.cfg.xml"));
		cellDAO = new CellDAO(config.buildSessionFactory());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void saveCellTest() {
		CellEntity entity = new CellEntity().setCellColor(CellColor.BLUE)
				.setCellState(CellState.DEAD).setPositionX(1).setPositionY(1);
		assertTrue(cellDAO.save(entity) > 0);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void findByIdTest() {
		CellEntity entity = new CellEntity().setCellColor(CellColor.BLUE)
				.setCellState(CellState.DEAD).setPositionX(1).setPositionY(1);
		long id = cellDAO.save(entity);
		CellEntity entity2 = cellDAO.findById(id);
		assertNotNull(entity2);
		assertTrue(entity.equals(entity2));
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void deleteByIdTest() {
		CellEntity entity = new CellEntity().setCellColor(CellColor.BLUE)
				.setCellState(CellState.DEAD).setPositionX(1).setPositionY(1);
		long id = cellDAO.save(entity);
		cellDAO.delete(id);
		assertNull(cellDAO.findById(id));
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void updateTest() {
		CellEntity entity = new CellEntity().setCellColor(CellColor.BLUE)
				.setCellState(CellState.DEAD).setPositionX(1).setPositionY(1);
		long id = cellDAO.save(entity);
		entity.setId(id).setCellState(CellState.LIVE);
		cellDAO.update(entity);
		CellEntity entity2 = cellDAO.findById(id);
		assertTrue(entity2.equals(entity));
	}

}
