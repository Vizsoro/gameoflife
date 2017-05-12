package it.challenges.gameoflife.database;

import org.hibernate.SessionFactory;

public class CycleDAO extends EntityHandler<CycleEntity> {
	
	private final SessionFactory factory;
	
	public CycleDAO(SessionFactory factory){
		super(factory);
		this.factory = factory;
	}

}
