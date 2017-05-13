package it.challenges.gameoflife.database;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class CycleDAO extends EntityHandler<CycleEntity> {

	private final SessionFactory factory;

	public CycleDAO(SessionFactory factory) {
		super(factory);
		this.factory = factory;
	}

	 
	public CycleEntity findByCycle(int cycleNum, boolean lazy){
		Session session = factory.openSession();
		CycleEntity entity = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			entity = session.createNamedQuery("CycleEntity.findByCycle", CycleEntity.class).setReadOnly(true)
				.setParameter("cycle", cycleNum).getSingleResult();
			if(!lazy){
				entity.getCellEntities().size();				
			}
		} catch (HibernateException | javax.persistence.NoResultException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return entity;
	}

}
