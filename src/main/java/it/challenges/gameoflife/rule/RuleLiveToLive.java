package it.challenges.gameoflife.rule;

import it.challenges.gameoflife.board.Cell;
import it.challenges.gameoflife.pojo.CellState;

public class RuleLiveToLive implements Rule {

	public boolean isAvaliable(Cell cell) {
		return cell.getLivingNeighbours() < 4 && cell.getLivingNeighbours() > 1
				&& CellState.LIVE.equals(cell.getState());
	}

	public void apply(Cell cell) {
		if (!CellState.LIVE.equals(cell.getState())) {
			throw new RuntimeException("Wrong rule!");
		} 
	}

}
