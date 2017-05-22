package it.challenges.gameoflife.database;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;


public class CellDAO extends EntityHandler<CellEntity> {
	
	private final SessionFactory factory;
	
	@Autowired
	public CellDAO(SessionFactory factory){
		super(factory);
		this.factory = factory;
	}
	
	public List<CellEntity> findByCycle(int cycle){
		Session session = factory.openSession();
		List<CellEntity> entity = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			entity = session.createNamedQuery("CellEntity.findByCycle", CellEntity.class).setReadOnly(true)
						.setParameter("cycle", cycle).getResultList();		
		} catch (HibernateException | javax.persistence.NoResultException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return entity;
	}
	
	public void clearAllCells(){
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.createNamedQuery("CellEntity.deleteAllCell").executeUpdate();
		session.close();
	}

}
