package com.caps.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Grass extends Block{
	
	private Rectangle bounds;
	private Color rgb;

	public Grass(float x, float y,Color rgb){
		super(x,y);
		this.rgb = rgb;
		bounds = new Rectangle((int) x, (int) y, 20, 20);
	}

	@Override
	public void tick() {
		
	}


	@Override
	public void render(Graphics g) {
		g.setColor(rgb);
		g.fillRect((int) x, (int) y, 20, 20);
		g.setColor(Color.green);

		
	}

	@Override
	public Rectangle getBoundsTotal() {
		return bounds;
	}
}
