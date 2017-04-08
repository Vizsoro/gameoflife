package it.challenges.gameoflife.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import it.challenges.gameoflife.pojo.CellColor;
import it.challenges.gameoflife.pojo.CellState;

@Component
public class BoardHandlerImplementation implements BoardHandler {

	private List<List<Cell>> board;
	private List<List<Cell>> previousBoard;
	private int boardSize;

	public List<List<Cell>> getPreviousBoard() {
		return Collections.unmodifiableList(previousBoard);
	}

	
	public NeighbourInfo findNeighbourInfo(Position position){
		NeighbourInfo info = new NeighbourInfo();
		int livingNeighbour = 0;
		int blue = 0;
		int green = 0;
		List<Cell> neighbours = getNeighbours(position);
		for(Cell neighbour : neighbours){
			if(CellState.LIVE.equals(neighbour.getState())){
				livingNeighbour++;
				if(CellColor.BLUE.equals(neighbour.getColor())){
					blue++;
				}else{
					green++;
				}
			}
		}		
		info.setColor(blue > green ? CellColor.BLUE : CellColor.GREEN);
		info.setLivingNeighbour(livingNeighbour);
		return info;
	}
	
	public List<Cell> getNeighbours(Position position){
		List<Cell> neighbours = new ArrayList<Cell>();
		int column = position.getY();
		int row = position.getX();
		int max = boardSize-1;
		int nextColumn = column == max ? 0 : column + 1;
		int previousColumn = column == 0 ? max : column-1;
		int nextRow = row == max ? 0 : row + 1;
		int previousRow = row == 0 ? max : row-1;
		neighbours.add(board.get(row).get(nextColumn));
		neighbours.add(board.get(row).get(previousColumn));
		neighbours.add(board.get(previousRow).get(nextColumn));
		neighbours.add(board.get(previousRow).get(column));
		neighbours.add(board.get(previousRow).get(previousColumn));
		neighbours.add(board.get(nextRow).get(nextColumn));
		neighbours.add(board.get(nextRow).get(column));
		neighbours.add(board.get(nextRow).get(previousColumn));
		return Collections.unmodifiableList(neighbours);
	}
	
	
	public List<List<Cell>> generateBoard(int size, double probability){
		this.boardSize = size;
		List<List<Cell>> board = new ArrayList<List<Cell>>();
		for (int i = 0; i < size; i++){
			List<Cell> row = new ArrayList<Cell>();
			for(int j = 0; j < size; j++){
				Cell cell = new Cell();
				cell.setPosition(new Position(i,j));
				cell.setState(Math.random() > probability ? CellState.LIVE : CellState.DEAD);
				cell.setColor(Math.random() > 0.5 ? CellColor.BLUE : CellColor.GREEN);
				row.add(cell);
			}
			board.add(row);
		}
		this.board = board;
		return Collections.unmodifiableList(board);
	}

	public void saveBoard(List<List<Cell>> board) {
		// TODO Auto-generated method stub
		this.board = board;
		
	}

	public List<List<Cell>> getBoard() {
		// TODO Auto-generated method stub
		return Collections.unmodifiableList(board);
	}
	
	public void prepareForNextCycle(){
		this.previousBoard = this.board;
	}

	public void setNeighbourInfo(Cell cell) {
		cell.setNeighbourInfo(findNeighbourInfo(cell.getPosition()));
		
	}
	
}
