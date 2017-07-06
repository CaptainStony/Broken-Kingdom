package com.caps.main;

import java.awt.Graphics;

import com.caps.objects.Colonist;

public class GameManager {

	private Game game;
	private Handler handler;
	private Grid grid;
	public GameManager(Game game) {
		this.game = game;
		handler = new Handler();
		handler.addObject(new Colonist(60, 60));
		grid = new Grid();
	}
	
	public void tick(){
		handler.tick();
	}
	
	public void render(Graphics g){
		handler.render(g);
		grid.render(g);
		
	}

}
