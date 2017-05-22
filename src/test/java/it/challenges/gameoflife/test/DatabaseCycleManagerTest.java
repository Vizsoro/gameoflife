package it.challenges.gameoflife.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Map;
import java.util.Optional;

import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import it.challenges.gameoflife.board.Board;
import it.challenges.gameoflife.board.BoardHandlerImplementation;
import it.challenges.gameoflife.board.Cell;
import it.challenges.gameoflife.cycle.CycleManagerInterface;
import it.challenges.gameoflife.cycle.DatabaseCycleManager;
import it.challenges.gameoflife.database.DatabaseHandler;
import it.challenges.gameoflife.pojo.CellState;
import it.challenges.gameoflife.rule.RuleDeadToLive;
import it.challenges.gameoflife.rule.RuleFactoryImplementation;
import it.challenges.gameoflife.rule.RuleLiveToDead;
import it.challenges.gameoflife.rule.RuleLiveToLive;

public class DatabaseCycleManagerTest{

	private CycleManagerInterface cycleManager;
	
	
	@Before
	public void init(){
		RuleFactoryImplementation ruleFactory = new RuleFactoryImplementation();
		ruleFactory.addRule(new RuleDeadToLive());
		ruleFactory.addRule(new RuleLiveToDead());
		ruleFactory.addRule(new RuleLiveToLive());
		Configuration config = new Configuration().configure(new File("src/test/hibernate/hibernate.cfg.xml"));
		cycleManager = new DatabaseCycleManager(new BoardHandlerImplementation(), ruleFactory, new DatabaseHandler(config));

	}
	@Test
	public void overPopulationTest() {
		cycleManager.startGame(10, 0);
		cycleManager.moveToNextCycle();
		assertTrue(cycleManager.getCurrentState().values().stream().flatMap(c -> c.values().stream())
				.allMatch(c -> CellState.DEAD.equals(c.getState())));
	}

	@Test
	public void previousAfteroverPopulationTest() {
		cycleManager.startGame(10, 0);
		cycleManager.moveToNextCycle();
		cycleManager.moveToPreviousCycle();
		assertTrue(cycleManager.getCurrentState().values().stream().flatMap(c -> c.values().stream())
				.allMatch(c -> CellState.LIVE.equals(c.getState())));
	}

	@Test
	public void underPopulationTest() {
		cycleManager.startGame(10, 1);
		assertTrue(cycleManager.getCurrentState().values().stream().flatMap(c -> c.values().stream())
				.allMatch(c -> CellState.DEAD.equals(c.getState())));
		cycleManager.moveToNextCycle();
		assertTrue(cycleManager.getCurrentState().values().stream().flatMap(c -> c.values().stream())
				.allMatch(c -> CellState.DEAD.equals(c.getState())));
	}

	@Test
	public void liveToLiveTest() {
		cycleManager.startGame(10, 0.5);
		Optional<Cell> liveCell = cycleManager.getCurrentState().values().stream().flatMap(c -> c.values().stream())
				.filter(c -> CellState.LIVE.equals(c.getState())
						&& (c.getLivingNeighbours() == 2 || c.getLivingNeighbours() == 3))
				.findAny();

		if (liveCell.isPresent()) {
			cycleManager.moveToNextCycle();
			int x = liveCell.get().getPosX();
			int y = liveCell.get().getPosY();
			assertTrue(cycleManager.getCurrentState().get(x).get(y).getState()
					.equals(CellState.LIVE));
		}

	}
	
	@Test
	public void deadToLiveTest() {
		cycleManager.startGame(10, 0.5);
		Optional<Cell> deadCell = cycleManager.getCurrentState().values().stream().flatMap(c -> c.values().stream())
				.filter(c -> CellState.DEAD.equals(c.getState())
						&& c.getLivingNeighbours() == 3)
				.findAny();

		if (deadCell.isPresent()) {
			cycleManager.moveToNextCycle();
			int x = deadCell.get().getPosX();
			int y = deadCell.get().getPosY();
			assertTrue(cycleManager.getCurrentState().get(x).get(y).getState()
					.equals(CellState.LIVE));
		}

	}
	
	@Test
	public void cycleManagementTest(){
		cycleManager.startGame(10, 0.5);
		Board currentBoard = cycleManager.getBoardCopy();
		Map<Integer,Map<Integer,Cell>> currentState = currentBoard.getCells();
		cycleManager.moveToNextCycle();
//		cycleManager.moveToNextCycle();
//		cycleManager.moveToPreviousCycle();
		cycleManager.moveToPreviousCycle();
		assertTrue(cycleManager.getCurrentState().values().stream().flatMap(c->c.values().stream()).parallel()
				.allMatch(c->currentState.get(c.getPosX()).get(c.getPosY()).equals(c)));
	}
	
	@Test
	public void sizeTest(){
		cycleManager.startGame(100, 0.5);
		cycleManager.moveToNextCycle();
		cycleManager.moveToNextCycle();;
		cycleManager.moveToPreviousCycle();
		assertTrue(cycleManager.getCurrentState().values().parallelStream().allMatch(l->l.size()==100));
	}
}
