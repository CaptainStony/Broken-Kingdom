package com.caps.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Tile {
	private int x,y;
	private Grid grid;
	public Rectangle bounds;
	public Tile(int x, int y, Grid grid) {
		this.x = x;
		this.y = y;
		this.grid = grid;
		bounds = new Rectangle(x, y, 20, 20);
	}


	
	public void render(Graphics g){
		g.setColor(Color.black);
		g.drawRect(x,y,20,20);
	}
}
