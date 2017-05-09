package it.challenges.gameoflife.database;

import java.time.Instant;
import java.util.Map;

import org.springframework.stereotype.Service;

import it.challenges.gameoflife.board.Board;
import it.challenges.gameoflife.board.Cell;
import it.challenges.gameoflife.board.Position;

@Service
public class DatabaseHandler {

	private EntityHandler<SimulationEntity> simulationHandler;
	private EntityHandler<BoardEntity> boardHandler;
	private EntityHandler<CellEntity> cellHandler;

	public DatabaseHandler() {
		simulationHandler = new SimulationDAO();
		boardHandler = new BoardDAO();
		cellHandler = new CellDAO();
	}

	public int saveNewSimulation(Board board) {
		SimulationEntity simulation = new SimulationEntity();
		simulation.setName(Instant.now().toString());
		int simulationId = simulationHandler.save(simulation);
		boardHandler.save(new BoardEntity().setSimulationId(simulationId).setSize(board.getBoardSize()));
		board.getCells().entrySet().parallelStream().map((entry)-> new CellEntity(entry.getKey(),entry.getValue()))
				.forEach(cell->cellHandler.save(cell.setCycle(0).setSimulationId(simulationId)));
		
		return simulationId;
	}

}
