package it.challenges.gameoflife.board;

import it.challenges.gameoflife.pojo.CellColor;
import it.challenges.gameoflife.pojo.CellState;

public class Cell {

	private CellState state;
	private CellColor color;
	private Position position;
	private NeighbourInfo neighbourInfo;
	
	
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
	
	
}
