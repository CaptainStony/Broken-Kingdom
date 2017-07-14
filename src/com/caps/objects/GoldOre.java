package com.caps.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GoldOre extends Block {
	private Rectangle bounds;
	public GoldOre(float x, float y){
		super(x,y);
		bounds = new Rectangle((int) x, (int) y, 20, 20);
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int) x, (int) y, 20, 20);
	}

	@Override
	public Rectangle getBoundsTotal() {
		return bounds;
	}
}
