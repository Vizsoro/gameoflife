package it.challenges.gameoflife.rule;

import it.challenges.gameoflife.board.Cell;
import it.challenges.gameoflife.pojo.CellState;

public class RuleDeadToLive implements Rule {

	public boolean isAvaliable(Cell cell) {
		return cell.getLivingNeighbours() == 3 && CellState.DEAD.equals(cell.getState());
	}

	public void apply(Cell cell) {
		cell.setState(CellState.LIVE);
		cell.setColor(cell.getSurroundingColor());
	}

}
