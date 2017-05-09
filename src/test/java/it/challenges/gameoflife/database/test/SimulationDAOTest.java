package it.challenges.gameoflife.database.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.challenges.gameoflife.database.SimulationDAO;
import it.challenges.gameoflife.database.SimulationEntity;

public class SimulationDAOTest {
	
	
	@Test
	public void findById(){
		SimulationDAO dao = new SimulationDAO();
		SimulationEntity entity = dao.findById(1); 
		assertTrue(entity instanceof SimulationEntity);
		assertTrue(entity.getName().equals("első teszt"));
	}
	
	@Test
	public void saveEntity(){
		SimulationDAO dao = new SimulationDAO();
		SimulationEntity entity = new SimulationEntity();
		entity.setName("második teszt");
		dao.saveSimulation(entity);		
	}
	
}
