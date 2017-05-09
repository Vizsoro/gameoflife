package it.challenges.gameoflife.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import it.challenges.gameoflife.board.Cell;
import it.challenges.gameoflife.board.Position;
import it.challenges.gameoflife.pojo.CellColor;
import it.challenges.gameoflife.pojo.CellState;

@Entity
@Table(name="cell")
public class CellEntity extends GameOfLifeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@Column
	private int simulationId;
	
	@Column
	private int positionX;
	
	@Column
	private int positionY;
	
	@Column
	private int cycle;
	
	@Column
	private CellState cellState;
	
	@Column
	private CellColor cellColor;
	
	public CellEntity(){
		
	}
	
	public CellEntity(Position position, Cell cell){
		this.positionX = position.getX();
		this.positionY = position.getY();
		this.cellState = cell.getState();
		this.cellColor = cell.getColor();
	}

	public int getId() {
		return id;
	}

	public CellEntity setId(int id) {
		this.id = id;
		return this;
	}

	public int getSimulationId() {
		return simulationId;
	}

	public CellEntity setSimulationId(int simulationId) {
		this.simulationId = simulationId;
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

	public int getCycle() {
		return cycle;
	}

	public CellEntity setCycle(int cycle) {
		this.cycle = cycle;
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
