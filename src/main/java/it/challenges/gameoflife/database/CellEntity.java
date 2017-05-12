package it.challenges.gameoflife.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import it.challenges.gameoflife.board.Cell;
import it.challenges.gameoflife.pojo.CellColor;
import it.challenges.gameoflife.pojo.CellState;

@Entity
public class CellEntity extends GameOfLifeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private long id;
	
	@Column
	private int positionX;
	
	@Column
	private int positionY;
	
	@Column
	private CellState cellState;
	
	@Column
	private CellColor cellColor;
	
	public CellEntity(){
		
	}
	
	public CellEntity(Cell cell){
		this.positionX = cell.getPosition().getX();
		this.positionY = cell.getPosition().getY();
		this.cellState = cell.getState();
		this.cellColor = cell.getColor();
	}

	public long getId() {
		return id;
	}

	public CellEntity setId(long id) {
		this.id = id;
		return this;
	}
	
	public int getPositionX() {
		return positionX;
	}

	public CellEntity setPositionX(int positionX) {
		this.positionX = positionX;
		return this;
	}

	public int getPositionY() {
		return positionY;
	}

	public CellEntity setPositionY(int positionY) {
		this.positionY = positionY;
		return this;
	}

	public CellState getCellState() {
		return cellState;
	}

	public CellEntity setCellState(CellState cellState) {
		this.cellState = cellState;
		return this;
	}

	public CellColor getCellColor() {
		return cellColor;
	}

	public CellEntity setCellColor(CellColor cellColor) {
		this.cellColor = cellColor;
		return this;
	}
	
	
	
}
