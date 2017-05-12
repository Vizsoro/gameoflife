package it.challenges.gameoflife.database.test;

import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import it.challenges.gameoflife.board.Board;
import it.challenges.gameoflife.board.BoardHandler;
import it.challenges.gameoflife.board.BoardHandlerImplementation;
import it.challenges.gameoflife.database.DatabaseHandler;

@Transactional
public class DatabaseHandlerTest {

	private DatabaseHandler handler;
	private BoardHandler boardHandler;
	private Configuration config;
	
	@Before
	public void init(){
		config = new Configuration().configure(new File("src/test/hibernate/hibernate.cfg.xml"));
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
	
	@After
	public void clearDataBase(){
		Session session = config.buildSessionFactory().openSession();
		
	}
}
