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
	private HUD hud;
	private MouseInputGame mouseInputGame;
	
	public int camX=0,camY=0;

	public GameManager(Game game) {
		this.game = game;
		handler = new Handler();
		handler.addObject(new Colonist(300, 300));
		grid = new Grid();
		mousetest = new MouseTest(game,this,handler,grid);
		hud = new HUD(this);
		mouseInputGame = new MouseInputGame(game,this, handler);
		game.addMouseListener(mousetest);
		game.addMouseMotionListener(mousetest);
		
		new WorldGenerator(new Random().nextInt(20), handler, grid);
		game.addKeyListener(new KeyInputGame(game,this, handler,hud));
		game.addKeyListener(hud);
		game.addMouseListener(mouseInputGame);
		game.addMouseWheelListener(mouseInputGame);
				
	}
	
	public void tick(){
		handler.tick();
		hud.tick();
	}
	public int camSpeed = 10;
	public boolean goScale = false;
	public float scale=1;

	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;

		//CAMERA MOVE
		if(hud.keyPress[2] == true){
			camX+=camSpeed;
		}
		if(hud.keyPress[3] == true){
			camX-=camSpeed;
		}
		if(hud.keyPress[0] == true){
			camY+=camSpeed;
		}
		if(hud.keyPress[1] == true){
			camY-=camSpeed;
		}
		
		//CAMERA SCROLL
		if (goScale){
			if(scale <= 1.6){
				scale -= (double)mouseInputGame.dir/20;			
			}else{
				scale = (float) 1.6;
			}
			if(scale >= 0.25){
				scale -= (double)mouseInputGame.dir/20;			
			}else{
				scale = (float) 0.25;
			}	
			goScale = false;
		}
		
		g2d.scale(scale, scale);//begin scale
		g2d.translate(camX, camY);//begin cam
		handler.render(g);
		grid.render(g);
		g2d.translate(-camX, -camY);//end cam
		g2d.scale(1/scale, 1/scale);//end scale

		
		hud.render(g);
	}

}
