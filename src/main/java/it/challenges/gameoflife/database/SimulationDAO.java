package it.challenges.gameoflife.database;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

@Service
public class SimulationDAO {
	 private SessionFactory factory; 
	
	
	public SimulationDAO() {
		try{
	         factory = new Configuration().configure().buildSessionFactory();
	      }catch (Throwable ex) { 
	         ex.printStackTrace(System.out);
	         throw ex;
	      }
	}

	public SimulationEntity findById(int id) {
		SimulationEntity simulationEntity = null;
		
		Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         simulationEntity = 
	                    (SimulationEntity)session.get(SimulationEntity.class, Integer.valueOf(1));
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return simulationEntity;
	}

	public int saveSimulation(SimulationEntity entity) {
		try{
	         factory = new Configuration().configure().buildSessionFactory();
	      }catch (Throwable ex) { 
	         ex.printStackTrace(System.out);
	         throw ex;
	      }
		Session session = factory.openSession();
	      Transaction tx = null;
	      Integer entityID = null;
	      try{
	         tx = session.beginTransaction();
	         entityID = (Integer) session.save(entity); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return entityID;
	}


}
