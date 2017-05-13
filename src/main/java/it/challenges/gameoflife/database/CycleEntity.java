package it.challenges.gameoflife.database;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import it.challenges.gameoflife.board.Cell;
import it.challenges.gameoflife.board.Position;

@Entity
@NamedQueries({
	@NamedQuery(name="CycleEntity.findByCycle", query = "select c from CycleEntity c where c.cycle = :cycle"),
})
public class CycleEntity extends GameOfLifeEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private int cycle;
	
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
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
	
	public CycleEntity setCellEntities(Map<Position, Cell> cellMap){
		this.cellEntities = cellMap.values().parallelStream().map(CellEntity::new).collect(Collectors.toList());
		return this;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cycle;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CycleEntity other = (CycleEntity) obj;
		if (cycle != other.cycle)
			return false;
		return true;
	}	

}
