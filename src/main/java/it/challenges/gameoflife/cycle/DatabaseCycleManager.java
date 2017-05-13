package it.challenges.gameoflife.cycle;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.challenges.gameoflife.board.Board;
import it.challenges.gameoflife.board.BoardHandler;
import it.challenges.gameoflife.board.Cell;
import it.challenges.gameoflife.board.Position;
import it.challenges.gameoflife.database.DatabaseHandler;
import it.challenges.gameoflife.rule.Rule;
import it.challenges.gameoflife.rule.RuleFactory;

@Component
public class DatabaseCycleManager implements CycleManagerInterface {
	@Autowired
	private BoardHandler boardHandler;
	@Autowired
	private RuleFactory ruleFactory;
	@Autowired
	private DatabaseHandler databaseHandler;
	
	private int actualCycle;
	private int maxCycle;
	private final static String ILLEGAL_ACTUALCYCLE = "The actual cycle value is illegal!";
	
	
	@Override
	public void moveToNextCycle() {
		
		if(actualCycle == maxCycle){
			++actualCycle;
			++maxCycle;
			Board board = boardHandler.getBoard();
			
			fillNeighbourInfo(board);
			
			calculateCycle(board);
			
			databaseHandler.saveCycle(actualCycle, board);
		} else if(actualCycle < maxCycle && actualCycle > 0) {
			++actualCycle;
			Map<Position, Cell> cycle = databaseHandler.getCycle(actualCycle);
			boardHandler.saveBoard(new Board(cycle));			
		} else {
			throw new IllegalStateException(ILLEGAL_ACTUALCYCLE);
		}


	}

	private void fillNeighbourInfo(final Board board) {
		board.getCells().values().parallelStream()
				.forEach(boardHandler::setNeighbourInfo);
	}

	private void calculateCycle(final Board board) {
		board.getCells().values().parallelStream()
				.forEach(DatabaseCycleManager.this::applyRules);
	}

	private void applyRules(Cell cell) {
		Set<Rule> rules = ruleFactory.findRules(cell);
		for (Rule rule : rules) {
			rule.apply(cell);
		}
	}

	@Override
	public void moveToPreviousCycle() {
		--actualCycle;
		boardHandler.saveBoard(new Board(databaseHandler.getCycle(actualCycle)));

	}

	@Override
	public Map<Integer, List<Cell>> getCurrentState() {
		Map<Integer, List<Cell>> collection = boardHandler.getBoard().getCells().values().parallelStream().collect(Collectors.groupingBy((Cell c)->{return c.getPosition().getX();}));
		collection.forEach((k,v)->Collections.sort(v, (c1,c2)->c1.getPosition().getY() - c2.getPosition().getY()));
		return collection;
	}

	@Override
	public void startGame(int size, double probability) {
		databaseHandler.clearCycles();
		actualCycle = maxCycle = 1;
		boardHandler.saveBoard(boardHandler.generateBoard(size, probability));
		fillNeighbourInfo(boardHandler.getBoard());
		databaseHandler.saveCycle(1, boardHandler.getBoard());
	}

	public DatabaseCycleManager(BoardHandler boardHandler, RuleFactory ruleFactory, DatabaseHandler databaseHandler){
		this.databaseHandler = databaseHandler;
		this.boardHandler = boardHandler;
		this.ruleFactory = ruleFactory;
	}

	@Override
	public boolean previousEnable() {
		// TODO Auto-generated method stub
		return actualCycle -1 > 0;
	}
}
