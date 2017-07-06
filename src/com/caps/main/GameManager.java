package com.caps.main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import com.caps.objects.Colonist;

public class GameManager {

	private Game game;
	private Handler handler;
	private Grid grid;
	private MouseTest mousetest;
	public int camX=0,camY=0;
	public double scaleX=1;
	public double scaleY=1;

	public GameManager(Game game) {
		this.game = game;
		handler = new Handler();
		handler.addObject(new Colonist(300, 300));
		grid = new Grid();
		mousetest = new MouseTest(game,handler,grid);
		game.addMouseListener(mousetest);
		new WorldGenerator(new Random().nextInt(20), handler, grid);
		game.addKeyListener(new KeyInputGame(game,this, handler));
		
		MouseInputGame mousenInputGame = new MouseInputGame(game,this, handler);
		game.addMouseListener(mousenInputGame);
		game.addMouseWheelListener(mousenInputGame);
	}
	
	public void tick(){
		handler.tick();
	}
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.scale(scaleX, scaleY);
		g2d.translate(camX, camY);//begin cam
		handler.render(g);
		grid.render(g);
		g2d.translate(-camX, -camY);//end cam

		//Camera
	}

}
