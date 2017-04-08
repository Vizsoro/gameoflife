package spring;

import static org.junit.Assert.*;

import org.junit.Test;

import it.challenges.gameoflife.board.Cell;
import it.challenges.gameoflife.board.NeighbourInfo;
import it.challenges.gameoflife.pojo.CellColor;
import it.challenges.gameoflife.pojo.CellState;
import it.challenges.gameoflife.rule.Rule;
import it.challenges.gameoflife.rule.RuleDeadToLive;
import it.challenges.gameoflife.rule.RuleLiveToDead;
import it.challenges.gameoflife.rule.RuleLiveToLive;

public class RuleTest {
	
	
	private Rule ruleDeadToLive = new RuleDeadToLive();
	private Rule ruleLiveToLive = new RuleLiveToLive();
	private Rule ruleLiveToDead = new RuleLiveToDead();
	
	
	@Test
	public void deadToLiveAvaliableTest(){
		NeighbourInfo info = new NeighbourInfo();
		info.setColor(CellColor.BLUE);
		info.setLivingNeighbour(3);
		Cell cell = new Cell();
		cell.setState(CellState.DEAD);
		cell.setNeighbourInfo(info);
		assertTrue(ruleDeadToLive.isAvaliable(cell));
	}
	
	@Test
	public void deadToLiveApplyTest(){
		NeighbourInfo info = new NeighbourInfo();
		info.setColor(CellColor.BLUE);
		info.setLivingNeighbour(3);
		Cell cell = new Cell();
		cell.setState(CellState.DEAD);
		cell.setNeighbourInfo(info);
		ruleDeadToLive.apply(cell);
		assertTrue(CellState.LIVE.equals(cell.getState()));
		assertTrue(CellColor.BLUE.equals(cell.getColor()));
	}
	
	@Test
	public void liveToLiveAvaliableTest(){
		NeighbourInfo info = new NeighbourInfo();
		info.setColor(CellColor.BLUE);
		info.setLivingNeighbour(3);
		Cell cell = new Cell();
		cell.setState(CellState.LIVE);
		cell.setNeighbourInfo(info);
		assertTrue(ruleLiveToLive.isAvaliable(cell));
		info.setLivingNeighbour(2);
		assertTrue(ruleLiveToLive.isAvaliable(cell));
	}
	
	@Test
	public void liveToLiveApplyTest(){
		NeighbourInfo info = new NeighbourInfo();
		info.setColor(CellColor.BLUE);
		info.setLivingNeighbour(3);
		Cell cell = new Cell();
		cell.setColor(CellColor.GREEN);
		cell.setState(CellState.LIVE);
		cell.setNeighbourInfo(info);
		ruleLiveToLive.apply(cell);
		assertTrue(CellState.LIVE.equals(cell.getState()));
		assertTrue(CellColor.GREEN.equals(cell.getColor()));
	}
	
	@Test
	public void liveToDeadAvaliableTest(){
		NeighbourInfo info = new NeighbourInfo();
		info.setColor(CellColor.BLUE);
		info.setLivingNeighbour(1);
		Cell cell = new Cell();
		cell.setState(CellState.LIVE);
		cell.setNeighbourInfo(info);
		assertTrue(ruleLiveToDead.isAvaliable(cell));
		info.setLivingNeighbour(5);
		assertTrue(ruleLiveToDead.isAvaliable(cell));
	}
	
	@Test
	public void liveToDeadApplyTest(){
		NeighbourInfo info = new NeighbourInfo();
		info.setColor(CellColor.BLUE);
		info.setLivingNeighbour(4);
		Cell cell = new Cell();
		cell.setColor(CellColor.GREEN);
		cell.setState(CellState.LIVE);
		cell.setNeighbourInfo(info);
		ruleLiveToDead.apply(cell);
		assertTrue(CellState.DEAD.equals(cell.getState()));
		assertTrue(CellColor.GREEN.equals(cell.getColor()));
	}
	
	

}
