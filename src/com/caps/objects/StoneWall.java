package com.caps.objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class StoneWall extends Block{
	
	private Rectangle bounds;
	public StoneWall(float x, float y){
		super(x,y);
		bounds = new Rectangle((int) x, (int) y, 20, 20);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {

		g.setColor(new Color(43,5,5));
		g.fillRect((int) x, (int) y, 20, 20);
		g.setColor(Color.white);
	}

	@Override
	public Rectangle getBoundsTotal() {
		return bounds;
	}
}
