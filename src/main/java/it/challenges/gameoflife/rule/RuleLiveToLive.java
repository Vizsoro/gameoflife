package it.challenges.gameoflife.rule;

import org.springframework.stereotype.Component;

import it.challenges.gameoflife.board.Cell;
import it.challenges.gameoflife.pojo.CellState;

@Component
public class RuleLiveToLive implements Rule {

	public boolean isAvaliable(Cell cell) {
		return cell.getLivingNeighbours() < 4 && cell.getLivingNeighbours() > 1
				&& CellState.LIVE.equals(cell.getState());
	}

	public void apply(Cell cell) {
		//no action needed
	}

}
