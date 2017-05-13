package it.challenges.gameoflife.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import it.challenges.gameoflife.board.BoardHandlerImplementation;
import it.challenges.gameoflife.board.Cell;
import it.challenges.gameoflife.board.Position;
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
		assertTrue(cycleManager.getCurrentState().values().stream().flatMap(c -> c.stream())
				.allMatch(c -> CellState.DEAD.equals(c.getState())));
	}

	@Test
	public void previousAfteroverPopulationTest() {
		cycleManager.startGame(10, 0);
		cycleManager.moveToNextCycle();
		cycleManager.moveToPreviousCycle();
		assertTrue(cycleManager.getCurrentState().values().stream().flatMap(c -> c.stream())
				.allMatch(c -> CellState.LIVE.equals(c.getState())));
	}

	@Test
	public void underPopulationTest() {
		cycleManager.startGame(10, 1);
		assertTrue(cycleManager.getCurrentState().values().stream().flatMap(c -> c.stream())
				.allMatch(c -> CellState.DEAD.equals(c.getState())));
		cycleManager.moveToNextCycle();
		assertTrue(cycleManager.getCurrentState().values().stream().flatMap(c -> c.stream())
				.allMatch(c -> CellState.DEAD.equals(c.getState())));
	}

	@Test
	public void liveToLiveTest() {
		cycleManager.startGame(10, 0.5);
		Optional<Cell> liveCell = cycleManager.getCurrentState().values().stream().flatMap(c -> c.stream())
				.filter(c -> CellState.LIVE.equals(c.getState())
						&& (c.getLivingNeighbours() == 2 || c.getLivingNeighbours() == 3))
				.findAny();

		if (liveCell.isPresent()) {
			cycleManager.moveToNextCycle();
			Position livePos = liveCell.get().getPosition();
			assertTrue(cycleManager.getCurrentState().get(livePos.getX()).get(livePos.getY()).getState()
					.equals(CellState.LIVE));
		}

	}
	
	@Test
	public void deadToLiveTest() {
		cycleManager.startGame(10, 0.5);
		Optional<Cell> deadCell = cycleManager.getCurrentState().values().stream().flatMap(c -> c.stream())
				.filter(c -> CellState.DEAD.equals(c.getState())
						&& c.getLivingNeighbours() == 3)
				.findAny();

		if (deadCell.isPresent()) {
			cycleManager.moveToNextCycle();
			Position pos = deadCell.get().getPosition();
			assertTrue(cycleManager.getCurrentState().get(pos.getX()).get(pos.getY()).getState()
					.equals(CellState.LIVE));
		}

	}
	
	@Test
	public void cycleManagementTest(){
		cycleManager.startGame(10, 0.5);
		Map<Integer, List<Cell>> currentState = cycleManager.getCurrentState();
		cycleManager.moveToNextCycle();
//		cycleManager.moveToNextCycle();
//		cycleManager.moveToPreviousCycle();
		cycleManager.moveToPreviousCycle();
		assertTrue(cycleManager.getCurrentState().values().stream().flatMap(c->c.stream()).parallel()
				.allMatch(c->currentState.get(c.getPosition().getX()).get(c.getPosition().getY()).equals(c)));
	}
}
