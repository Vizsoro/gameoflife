package it.challenges.gameoflife.database;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class CellDAO extends EntityHandler<CellEntity> {
	
	@Autowired
	public CellDAO(SessionFactory factory){
		super(factory);
	}

}
