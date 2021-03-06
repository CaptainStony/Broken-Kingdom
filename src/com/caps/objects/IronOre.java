package com.caps.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class IronOre extends Block {
	private Rectangle bounds;
	public IronOre(float x, float y){
		super(x,y);
		bounds = new Rectangle((int) x, (int) y, 20, 20);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(108, 62, 2));
		g.fillRect((int) x, (int) y, 20, 20);
	}

	@Override
	public Rectangle getBoundsTotal() {
		return bounds;
	}
}
