package it.challenges.gameoflife.cycle;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.challenges.gameoflife.board.BoardHandler;
import it.challenges.gameoflife.board.Cell;
import it.challenges.gameoflife.rule.Rule;
import it.challenges.gameoflife.rule.RuleFactory;

@Component
public class CycleManager implements CycleManagerInterface {

	@Autowired
	private BoardHandler boardHandler;
	@Autowired
	private RuleFactory ruleFactory;

	@Override
	public void moveToNextCycle() {

		List<List<Cell>> board = boardHandler.getBoard();

		fillNeighbourInfo(board);

		boardHandler.savePreviousBoard(createCopy(board));

		calculateCycle(board);

	}

	private List<List<Cell>> createCopy(List<List<Cell>> board) {
		List<List<Cell>> copyBoard = new ArrayList<List<Cell>>();
		for (List<Cell> row : board) {
			List<Cell> newRow = new ArrayList<Cell>();
			for (Cell cell : row) {
				newRow.add(new Cell(cell));
			}
			copyBoard.add(newRow);
		}
		return copyBoard;
	}

	private void fillNeighbourInfo(final List<List<Cell>> board) {
		board.parallelStream()
				.forEach((List<Cell> row) -> row.parallelStream().forEach(boardHandler::setNeighbourInfo));
	}

	private void calculateCycle(final List<List<Cell>> board) {
		board.parallelStream()
				.forEach((List<Cell> row) -> row.parallelStream().forEach(CycleManager.this::applyRules));
	}

	private void applyRules(Cell cell) {
		Set<Rule> rules = ruleFactory.findRules(cell);
		for (Rule rule : rules) {
			rule.apply(cell);
		}
	}

	@Override
	public void moveToPreviousCycle() {
		boardHandler.saveBoard(boardHandler.getPreviousBoard());

	}

	@Override
	public List<List<Cell>> getCurrentState() {
		return boardHandler.getBoard();
	}

	@Override
	public void startGame(int size, double probability) {
		boardHandler.saveBoard(boardHandler.generateBoard(size, probability));
	}

}
