package it.challenges.gameoflife.database;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import it.challenges.gameoflife.board.Board;
import it.challenges.gameoflife.board.Cell;

@Service
public class DatabaseHandler {

	
	private CellDAO cellDAO;
	
	private Map<Integer,Map<Integer,Cell>> originalCells;
	
	private SessionFactory factory;

	public DatabaseHandler() {
		try {
			factory = new Configuration().configure().buildSessionFactory();
			populateDAOs(factory);
		} catch (Throwable ex) {
			ex.printStackTrace(System.out);
			throw ex;
		}
	}

	public DatabaseHandler(Configuration config) {
		try {
			factory = config.buildSessionFactory();
			populateDAOs(factory);
		} catch (Throwable ex) {
			ex.printStackTrace(System.out);
			throw ex;
		}
	}

	public synchronized void saveCycle(int cycle, Board board) {
		Stream<Cell> cellsToSave;
		if(cycle == 1){
			originalCells = board.copy().getCells();
			cellsToSave = board.getCells().values().parallelStream().flatMap(map->map.values().stream());
		} else {
			cellsToSave = board.getCells().values().parallelStream().flatMap(map->map.values().stream())
							.filter(cell -> !originalCells.get(cell.getPosX()).get(cell.getPosY()).equals(cell));
		}
		cellsToSave.map(CellEntity::new)
				.forEach(entity -> {entity.setCycle(cycle); cellDAO.save(entity) ;});
	}

	public synchronized  Map<Integer,Map<Integer,Cell>> getCycle(int cycleNumber){
		if(cycleNumber == 1 || originalCells == null){
			return originalCells;
		}
		List<CellEntity> changedCells = cellDAO.findByCycle(cycleNumber);
		Map<Integer,Map<Integer,Cell>> cells = new TreeMap<>();
		int size = originalCells.size();
		for(int j = 0; j< size; ++j){
			Map<Integer,Cell> row = new TreeMap<>();
			for(int k = 0; k < size; ++k){
				row.put(k, new Cell(originalCells.get(j).get(k)));
			}
			cells.put(j, row);
		}
		for(CellEntity entity : changedCells){
			cells.get(entity.getPositionX()).get(entity.getPositionY()).setColor(entity.getCellColor()).setState(entity.getCellState());
		}		
		return cells;
	}

	public synchronized void clearCycles() {
		originalCells = null;
		cellDAO.clearAllCells();
	}
	
	private void populateDAOs(SessionFactory factory){
		cellDAO = new CellDAO(factory);
	}
}
