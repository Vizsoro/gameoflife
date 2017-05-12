package it.challenges.gameoflife.database;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CycleEntity extends GameOfLifeEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private int cycle;
	
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<CellEntity> cellEntities;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public int getCycle() {
		return cycle;
	}


	public CycleEntity setCycle(int cycle) {
		this.cycle = cycle;
		return this;
	}


	public List<CellEntity> getCellEntities() {
		return cellEntities;
	}


	public CycleEntity setCellEntities(List<CellEntity> cellEntities) {
		this.cellEntities = cellEntities;
		return this;
	}
	
	
	

}
