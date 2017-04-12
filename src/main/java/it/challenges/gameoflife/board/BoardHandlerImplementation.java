package it.challenges.gameoflife.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import it.challenges.gameoflife.pojo.CellColor;
import it.challenges.gameoflife.pojo.CellState;

@Component
public class BoardHandlerImplementation implements BoardHandler {

	private Board board;
	private Board previousBoard;

	@Override
	public NeighbourInfo findNeighbourInfo(Position position) {
		NeighbourInfo info = new NeighbourInfo();
		int livingNeighbour = 0;
		int blue = 0;
		int green = 0;
		List<Cell> neighbours = getNeighbours(position);
		for (Cell neighbour : neighbours) {
			if (CellState.LIVE.equals(neighbour.getState())) {
				livingNeighbour++;
				if (CellColor.BLUE.equals(neighbour.getColor())) {
					blue++;
				} else {
					green++;
				}
			}
		}
		info.setColor(blue > green ? CellColor.BLUE : CellColor.GREEN);
		info.setLivingNeighbour(livingNeighbour);
		return info;
	}

	@Override
	public List<Cell> getNeighbours(Position position) {
		List<Cell> neighbours = new ArrayList<Cell>();
		List<List<Cell>> cells = board.getCells();
		int column = position.getY();
		int row = position.getX();
		int max = board.getBoardSize() - 1;
		int nextColumn = column == max ? 0 : column + 1;
		int previousColumn = column == 0 ? max : column - 1;
		int nextRow = row == max ? 0 : row + 1;
		int previousRow = row == 0 ? max : row - 1;
		neighbours.add(cells.get(row).get(nextColumn));
		neighbours.add(cells.get(row).get(previousColumn));
		neighbours.add(cells.get(previousRow).get(nextColumn));
		neighbours.add(cells.get(previousRow).get(column));
		neighbours.add(cells.get(previousRow).get(previousColumn));
		neighbours.add(cells.get(nextRow).get(nextColumn));
		neighbours.add(cells.get(nextRow).get(column));
		neighbours.add(cells.get(nextRow).get(previousColumn));
		return Collections.unmodifiableList(neighbours);
	}

	@Override
	public Board generateBoard(int size, double probability) {
		List<List<Cell>> board = new ArrayList<List<Cell>>(size);
		for (int i = 0; i < size; i++) {
			List<Cell> row = new ArrayList<Cell>(size);
			for (int j = 0; j < size; j++) {
				Cell cell = new Cell();
				cell.setPosition(new Position(i, j));
				cell.setState(Math.random() > probability ? CellState.LIVE : CellState.DEAD);
				cell.setColor(Math.random() > 0.5 ? CellColor.BLUE : CellColor.GREEN);
				row.add(cell);
			}
			board.add(row);
		}
		return new Board(board);
	}

	@Override
	public void saveBoard(Board board) {
		this.board = board;

	}

	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public void setNeighbourInfo(Cell cell) {
		cell.setNeighbourInfo(findNeighbourInfo(cell.getPosition()));

	}

	@Override
	public Board getPreviousBoard() {
		return previousBoard;
	}

	@Override
	public void savePreviousBoard(Board board) {
		this.previousBoard = board;
	}

}
