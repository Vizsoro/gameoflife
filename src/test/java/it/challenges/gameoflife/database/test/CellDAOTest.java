package it.challenges.gameoflife.database.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.challenges.gameoflife.database.CellDAO;
import it.challenges.gameoflife.database.CellEntity;
import it.challenges.gameoflife.pojo.CellColor;
import it.challenges.gameoflife.pojo.CellState;

public class CellDAOTest {

	private CellDAO cellDAO = new CellDAO();

	@Test
	public void saveBoardTest() {
		CellEntity entity = new CellEntity().setSimulationId(1).setCellColor(CellColor.BLUE)
				.setCellState(CellState.DEAD).setCycle(1).setPositionX(1).setPositionY(1);
		assertTrue(cellDAO.save(entity) > 0);

	}

}
