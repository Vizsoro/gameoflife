package it.challenges.gameoflife.database;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.core.GenericTypeResolver;


class EntityHandler<E extends GameOfLifeEntity> {

	private final SessionFactory factory;
	private final Class<E> typeParameterClass;
	
	@SuppressWarnings("unchecked")
	public EntityHandler(SessionFactory factory){
		this.typeParameterClass = (Class<E>) GenericTypeResolver.resolveTypeArgument(getClass(), EntityHandler.class);
		this.factory = factory;
	}

	public long save(E entity) {
		Session session = factory.openSession();
		Transaction tx = null;
		long entityID = -1;
		try {
			tx = session.beginTransaction();
			entityID = (Long) session.save(entity);
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

	public E findById(int id) {
		E entity = null;

		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			entity = (E) session.get(typeParameterClass, id);
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return entity;
	}

	public void delete(int id){
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			E entity = (E) session.get(typeParameterClass, id);
			session.delete(entity);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void update(E entity){
		Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
			 session.update(entity); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	}
}
