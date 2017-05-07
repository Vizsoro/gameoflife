package it.challenges.gameoflife.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="board")
public class BoardEntity extends GameOfLifeEntity {
	
	@Id
	private int id;
	
	@Column
	private int size;
	
	@Column
	private int simulationId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getSimulationId() {
		return simulationId;
	}

	public void setSimulationId(int simulationId) {
		this.simulationId = simulationId;
	}

	
}
