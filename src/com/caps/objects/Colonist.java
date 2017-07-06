package com.caps.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.caps.main.GridCell;

public class Colonist extends Object{
	
	public LinkedList<GridCell> path;
	public LinkedList<GridCell> closedList = new LinkedList<GridCell>();
	public LinkedList<GridCell> openList = new LinkedList<GridCell>();
	
	public Colonist(float x, float y) {
		super(x, y);
		
	}

	
	@Override
	public void tick() {
		x += velX;
		y += velY;
	}
		
	public void render(Graphics g) {

		g.setColor(Color.yellow);
		g.fillRect((int)x, (int)y, 20, 20);

	}
	@Override
	public Rectangle getBoundsTotal() {
		return new Rectangle((int)x, (int)y, 20, 20);

	}

}
