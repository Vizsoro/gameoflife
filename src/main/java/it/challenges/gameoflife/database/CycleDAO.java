package it.challenges.gameoflife.database;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

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
	
	public boolean clearAllCycle(){
		boolean succes;
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<CycleEntity> resultList = session.createNamedQuery("CycleEntity.allCycle").getResultList();
			for(CycleEntity entity : resultList){
				session.delete(entity);				
			}
			tx.commit();
			succes = true;
		} catch (HibernateException | IllegalArgumentException e) {
			succes = false;
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return succes;
	}

}
