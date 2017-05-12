package it.challenges.gameoflife.database;

import org.hibernate.SessionFactory;

public class CellDAO extends EntityHandler<CellEntity> {
	
	public CellDAO(SessionFactory factory){
		super(factory);
	}

}
