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
import it.challenges.gameoflife.rule.Rule;
import it.challenges.gameoflife.rule.RuleFactory;

//@Component
public class CycleManager implements CycleManagerInterface {

	@Autowired
	private BoardHandler boardHandler;
	@Autowired
	private RuleFactory ruleFactory;
	private boolean previous;

	@Override
	public void moveToNextCycle() {

		previous = true;

		Board board = boardHandler.getBoard();

		fillNeighbourInfo(board);

		boardHandler.savePreviousBoard(board.copy());

		calculateCycle(board);

	}

	private void fillNeighbourInfo(final Board board) {
		board.getCells().values().parallelStream().flatMap(map->map.values().stream())
				.forEach(boardHandler::setNeighbourInfo);
	}

	private void calculateCycle(final Board board) {
		board.getCells().values().parallelStream().flatMap(map->map.values().stream())
				.forEach(CycleManager.this::applyRules);
	}

	private void applyRules(Cell cell) {
		Set<Rule> rules = ruleFactory.findRules(cell);
		for (Rule rule : rules) {
			rule.apply(cell);
		}
	}

	@Override
	public void moveToPreviousCycle() {
		previous = false;
		boardHandler.saveBoard(boardHandler.getPreviousBoard());

	}

	@Override
	public Map<Integer, Map<Integer, Cell>> getCurrentState() {
		return boardHandler.getBoard().getCells();
	}

	@Override
	public void startGame(int size, double probability) {
		boardHandler.saveBoard(boardHandler.generateBoard(size, probability));
		fillNeighbourInfo(boardHandler.getBoard());
	}

	public CycleManager(BoardHandler boardHandler, RuleFactory ruleFactory) {
		this.boardHandler = boardHandler;
		this.ruleFactory = ruleFactory;
	}

	@Override
	public boolean previousEnable() {
		return previous;
	}

}
