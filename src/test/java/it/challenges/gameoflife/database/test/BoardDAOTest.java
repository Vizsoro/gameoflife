package it.challenges.gameoflife.database.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.challenges.gameoflife.database.BoardDAO;
import it.challenges.gameoflife.database.BoardEntity;

public class BoardDAOTest {


	private BoardDAO boardDAO = new BoardDAO();
	
	
	@Test
	public void findByIdTest(){
		assertNotNull(boardDAO.findById(1));
	}

	@Test
	public void saveBoardTest(){
		BoardEntity entity = new BoardEntity().setSimulationId(1).setSize(10);
		assertTrue(boardDAO.save(entity)>0);
		
	}
	
}
