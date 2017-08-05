package com.caps.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.caps.objects.GameObject;

public class GridCell {

	protected int x,y;
	private Grid grid;
	
	protected Rectangle bounds;
	public boolean render = false;
	public int G=0,H=0;
	public LinkedList<GameObject> objectList = new LinkedList<GameObject>();

	public GridCell previousCell;

	public GridCell(int x, int y, Grid grid) {
		this.x = x;
		this.y = y;
		this.grid = grid;
		bounds = new Rectangle(x, y, 10, 10);
	}
	
	public void render(Graphics g){
		if (render == true){
			g.setColor(Color.red);
			g.drawRect(x,y,10,10);
		}

	}
	
	public boolean isDiagonal(GridCell gridCell){
		if(gridCell.getX() == getX() || gridCell.getY() == getY()){
			return false;
		}
		return true;
	}
	
	public LinkedList<GridCell> getAvailableAdjacentCells(){
		LinkedList<GridCell> ad = new LinkedList<GridCell>();
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				GridCell adjCell = grid.cordsToGridCells(getX()-i*10, getY()-j*10);							
				Tile adjTile = grid.cordsToTile(getX()-i*10, getY()-j*10);
				boolean valid = !adjTile.isWall();
				if(adjCell == this){
					valid = false;
				}
				if(valid){
					ad.add(adjCell);
				}
			}
		}
		return ad;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getG() {
		return G;
	}

	public void setG(int g) {
		G = g;
	}

	public int getH() {
		return H;
	}

	public void setH(int h) {
		H = h;
	}
	public int getF() {
		return G+H;
	}


	public GridCell getPreviousCell() {
		return previousCell;
	}

	public void setPreviousCell(GridCell previousCell) {
		this.previousCell = previousCell;
	}

}
