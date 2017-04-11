package it.challenges.gameoflife.board;

import it.challenges.gameoflife.pojo.CellColor;
import it.challenges.gameoflife.pojo.CellState;

public class Cell {

	private CellState state;
	private CellColor color;
	private Position position;
	private NeighbourInfo neighbourInfo;
	
	public Cell(){
		
	}
	
	public Cell(Cell cell){
		this.state = cell.state;
		this.color = cell.color;
		this.position = cell.position;
		this.neighbourInfo = new NeighbourInfo(cell.neighbourInfo);
	}
	
	public CellState getState() {
		return state;
	}
	public void setState(CellState state) {
		this.state = state;
	}
	public CellColor getColor() {
		return color;
	}
	public void setColor(CellColor color) {
		this.color = color;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public NeighbourInfo getNeighbourInfo() {
		return neighbourInfo;
	}
	public void setNeighbourInfo(NeighbourInfo neighbourInfo) {
		this.neighbourInfo = neighbourInfo;
	}
	
	public int getLivingNeighbours(){
		return neighbourInfo.getLivingNeighbour();
	}
	
	public CellColor getSurroundingColor(){
		return neighbourInfo.getColor();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		Cell other = (Cell) obj;
		if (color != other.color)
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (state != other.state)
			return false;
		return true;
	}
	
	
	public boolean equals(Position position){
		if(this.position != null){
			return this.position.equals(position);			
		}else{
			return false;
		}
	}
	
}
