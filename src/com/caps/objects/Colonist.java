package com.caps.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Colonist extends Object{

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
		g.fillRect((int)x, (int)y, 30, 30);

	}
	@Override
	public Rectangle getBoundsTotal() {
		return new Rectangle((int)x, (int)y, 30, 30);

	}

}
