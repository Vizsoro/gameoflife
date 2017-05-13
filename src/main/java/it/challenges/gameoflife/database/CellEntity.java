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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cellColor == null) ? 0 : cellColor.hashCode());
		result = prime * result + ((cellState == null) ? 0 : cellState.hashCode());
		result = prime * result + positionX;
		result = prime * result + positionY;
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
		CellEntity other = (CellEntity) obj;
		if (cellColor != other.cellColor)
			return false;
		if (cellState != other.cellState)
			return false;
		if (positionX != other.positionX)
			return false;
		if (positionY != other.positionY)
			return false;
		return true;
	}
	
	
	
}
