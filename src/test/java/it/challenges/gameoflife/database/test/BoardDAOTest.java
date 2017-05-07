package it.challenges.gameoflife.database.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import it.challenges.gameoflife.board.Board;
import it.challenges.gameoflife.database.BoardDAO;

public class BoardDAOTest {


	private BoardDAO boardDAO = new BoardDAO();
	
	
	@Test
	public void findByIdTest(){
		assertNotNull(boardDAO.findById(1));
	}

	
}
