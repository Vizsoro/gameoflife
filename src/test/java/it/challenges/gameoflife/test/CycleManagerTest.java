package it.challenges.gameoflife.test;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import it.challenges.gameoflife.board.BoardHandlerImplementation;
import it.challenges.gameoflife.board.Cell;
import it.challenges.gameoflife.board.Position;
import it.challenges.gameoflife.cycle.CycleManager;
import it.challenges.gameoflife.pojo.CellState;
import it.challenges.gameoflife.rule.RuleDeadToLive;
import it.challenges.gameoflife.rule.RuleFactoryImplementation;
import it.challenges.gameoflife.rule.RuleLiveToDead;
import it.challenges.gameoflife.rule.RuleLiveToLive;

public class CycleManagerTest {

	private CycleManager cycleManager;

	@Before
	public void init() {
		RuleFactoryImplementation ruleFactory = new RuleFactoryImplementation();
		ruleFactory.addRule(new RuleDeadToLive());
		ruleFactory.addRule(new RuleLiveToDead());
		ruleFactory.addRule(new RuleLiveToLive());
		cycleManager = new CycleManager(new BoardHandlerImplementation(), ruleFactory);

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
		Optional<Cell> liveCell = cycleManager.getCurrentState().values().stream().flatMap(c -> c.stream())
				.filter(c -> CellState.DEAD.equals(c.getState())
						&& c.getLivingNeighbours() == 3)
				.findAny();

		if (liveCell.isPresent()) {
			cycleManager.moveToNextCycle();
			Position livePos = liveCell.get().getPosition();
			assertTrue(cycleManager.getCurrentState().get(livePos.getX()).get(livePos.getY()).getState()
					.equals(CellState.LIVE));
		}

	}

}
