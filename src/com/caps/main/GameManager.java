package com.caps.main;

import java.awt.Graphics;
import java.util.Random;

import com.caps.objects.Colonist;

public class GameManager {

	private Game game;
	private Handler handler;
	private Grid grid;
	private MouseTest mousetest;
	public GameManager(Game game) {
		this.game = game;
		handler = new Handler();
		handler.addObject(new Colonist(300, 300));
		grid = new Grid();
		mousetest = new MouseTest(game,handler,grid);
		game.addMouseListener(mousetest);
		new WorldGenerator(new Random().nextInt(20), handler, grid);
	}
	
	public void tick(){
		handler.tick();
	}
	
	public void render(Graphics g){
		handler.render(g);
		grid.render(g);
		
	}

}
