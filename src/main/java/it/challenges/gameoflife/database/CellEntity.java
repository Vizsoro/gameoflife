package it.challenges.gameoflife.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import it.challenges.gameoflife.pojo.CellColor;
import it.challenges.gameoflife.pojo.CellState;

@Entity
@Table(name="cell")
public class CellEntity extends GameOfLifeEntity {

	@Id
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSimulationId() {
		return simulationId;
	}

	public void setSimulationId(int simulationId) {
		this.simulationId = simulationId;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public int getCycle() {
		return cycle;
	}

	public void setCycle(int cycle) {
		this.cycle = cycle;
	}

	public CellState getCellState() {
		return cellState;
	}

	public void setCellState(CellState cellState) {
		this.cellState = cellState;
	}

	public CellColor getCellColor() {
		return cellColor;
	}

	public void setCellColor(CellColor cellColor) {
		this.cellColor = cellColor;
	}
	
	
	
}
