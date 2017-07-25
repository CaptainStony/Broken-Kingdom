package com.caps.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class GridCell {

	protected int x,y;
	private Grid grid;
	
	protected Rectangle bounds;
	public boolean render = false;
	public float G=0,H=0,F=0;
	public LinkedList<GridCell> allAdjCells = new LinkedList<GridCell>();
	
	public LinkedList<Object> objectList = new LinkedList<Object>();

	public GridCell(int x, int y, Grid grid) {
		this.x = x;
		this.y = y;
		this.grid = grid;
		bounds = new Rectangle(x, y, 10, 10);
	}
	
	public void render(Graphics g){
		if (render == true){
			g.setColor(Color.green);
			g.drawRect(x,y,10,10);
		}

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
	
	public LinkedList<GridCell> getAllAdjCells() {
		return allAdjCells;
	}

	public void setAllAdjCells(LinkedList<GridCell> allAdjCells) {
		this.allAdjCells = allAdjCells;
	}
}
