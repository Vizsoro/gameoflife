package it.challenges.gameoflife.database;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class SimulationDAO extends EntityHandler<SimulationEntity>{
	private SessionFactory factory;

	public SimulationDAO() {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			ex.printStackTrace(System.out);
			throw ex;
		}
	}

	public int saveSimulation(SimulationEntity entity) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer entityID = null;
		try {
			tx = session.beginTransaction();
			entityID = (Integer) session.save(entity);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return entityID;
	}

}
