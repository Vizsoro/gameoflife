package it.challenges.gameoflife.database;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import it.challenges.gameoflife.board.Board;

@Service
public class DatabaseHandler {

	private EntityHandler<CellEntity> cellHandler;
	
	private SessionFactory factory;

	public DatabaseHandler() {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			ex.printStackTrace(System.out);
			throw ex;
		}
	}
	
	public DatabaseHandler(Configuration config){
		try {
			factory = config.buildSessionFactory();
		} catch (Throwable ex) {
			ex.printStackTrace(System.out);
			throw ex;
		}
	}
	
	public long saveCycle(int cycle, Board board){
		List<CellEntity> cellEntities = board.getCells().values().parallelStream().map(CellEntity::new).collect(Collectors.toList());
		CycleEntity cycleEntity = new CycleEntity().setCellEntities(cellEntities).setCycle(cycle);
		return new CycleDAO(factory).save(cycleEntity);		
	}

}
