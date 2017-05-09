package it.challenges.gameoflife.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="board")
public class BoardEntity extends GameOfLifeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@Column
	private int size;
	
	@Column
	private int simulationId;

	public int getId() {
		return id;
	}

	public BoardEntity setId(int id) {
		this.id = id;
		return this;
	}

	public int getSize() {
		return size;
	}

	public BoardEntity setSize(int size) {
		this.size = size;
		return this;
	}

	public int getSimulationId() {
		return simulationId;
	}

	public BoardEntity setSimulationId(int simulationId) {
		this.simulationId = simulationId;
		return this;
	}

	
}
