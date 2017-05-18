package it.challenges.gameoflife.board;

import it.challenges.gameoflife.pojo.CellColor;
import it.challenges.gameoflife.pojo.CellState;

public class Cell {

	private CellState state;
	private CellColor color;
	private int posX;
	private int posY;
	private NeighbourInfo neighbourInfo;
	
	public Cell(){
		
	}
	
	public Cell(Cell cell){
		this.state = cell.state;
		this.color = cell.color;
		
		if(cell.neighbourInfo != null){
			this.neighbourInfo = new NeighbourInfo(cell.neighbourInfo);			
		}
	}
	
	public CellState getState() {
		return state;
	}
	public Cell setState(CellState state) {
		this.state = state;
		return this;
	}
	public CellColor getColor() {
		return color;
	}
	public Cell setColor(CellColor color) {
		this.color = color;
		return this;
	}
	
	public NeighbourInfo getNeighbourInfo() {
		return neighbourInfo;
	}
	public Cell setNeighbourInfo(NeighbourInfo neighbourInfo) {
		this.neighbourInfo = neighbourInfo;
		return this;
	}
	
	public int getLivingNeighbours(){
		return neighbourInfo.getLivingNeighbour();
	}
	
	public CellColor getSurroundingColor(){
		return neighbourInfo.getColor();
	}

	public int getPosX() {
		return posX;
	}

	public Cell setPosX(int posX) {
		this.posX = posX;
		return this;
	}

	public int getPosY() {
		return posY;
	}

	public Cell setPosY(int posY) {
		this.posY = posY;
		return this;
	}

	
}
