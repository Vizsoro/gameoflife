package it.challenges.gameoflife.board;

import it.challenges.gameoflife.pojo.CellColor;

public class NeighbourInfo {

	private CellColor color;
	private int livingNeighbour;
	public CellColor getColor() {
		return color;
	}
	public void setColor(CellColor color) {
		this.color = color;
	}
	public int getLivingNeighbour() {
		return livingNeighbour;
	}
	public void setLivingNeighbour(int livingNeighbour) {
		this.livingNeighbour = livingNeighbour;
	}
	
	
	
}
