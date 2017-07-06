package com.caps.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GridCell {

	private int x,y;
	private Grid grid;
	public Rectangle bounds;

	public GridCell(int x, int y, Grid grid) {
		this.x = x;
		this.y = y;
		this.grid = grid;
		bounds = new Rectangle(x, y, 10, 10);
	}
	
	public void render(Graphics g){
		g.setColor(Color.green);
		g.drawRect(x,y,10,10);
	}

}
