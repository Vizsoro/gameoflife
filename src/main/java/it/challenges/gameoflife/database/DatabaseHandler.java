package it.challenges.gameoflife.database;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import it.challenges.gameoflife.board.Board;
import it.challenges.gameoflife.board.Cell;
import it.challenges.gameoflife.board.Position;

@Service
public class DatabaseHandler {

	
	private CellDAO cellDAO;

	private CycleDAO cycleDAO;
	
	private SessionFactory factory;

	public DatabaseHandler() {
		try {
			factory = new Configuration().configure().buildSessionFactory();
			cellDAO = new CellDAO(factory);
			cycleDAO = new CycleDAO(factory);
		} catch (Throwable ex) {
			ex.printStackTrace(System.out);
			throw ex;
		}
	}

	public DatabaseHandler(Configuration config) {
		try {
			factory = config.buildSessionFactory();
			cellDAO = new CellDAO(factory);
			cycleDAO = new CycleDAO(factory);
		} catch (Throwable ex) {
			ex.printStackTrace(System.out);
			throw ex;
		}
	}

	public synchronized long saveCycle(int cycle, Board board) {
		List<CellEntity> cellEntities = board.getCells().values().parallelStream().map(CellEntity::new)
				.collect(Collectors.toList());
		CycleEntity cycleEntity = new CycleEntity().setCellEntities(cellEntities).setCycle(cycle);
		return cycleDAO.save(cycleEntity);
	}

	public synchronized  Map<Position, Cell> getCycle(int cycleNumber){
		CycleEntity cycleEntity = cycleDAO.findByCycle(cycleNumber, false);
		if(cycleEntity != null){
			Map<Position, Cell> cellMap = new ConcurrentHashMap<>();
			cycleEntity.getCellEntities().stream().map(entity -> new Cell().setColor(entity.getCellColor())
					.setPosition(new Position(entity.getPositionX(), entity.getPositionY())).setState(entity.getCellState()))
					.forEach(c->cellMap.put(c.getPosition(), c));				
			
			return cellMap;
		} else {
			return null;
		}
	}

	public synchronized void clearCycles() {
		cycleDAO.clearAllCycle();
	}
}
