package com.caps.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.Random;

public class GameManager {

	private Game game;
	private Handler handler;
	private Grid grid;
	private HUD hud;
	private MouseInputGame mouseInputGame;
	private PreGame preGame;
	
	public int camX=0,camY=0;
	public boolean preGameActive = true;

	public int wood=0,stone=0,gold=0,iron=0;
	
	public LinkedList<Object> selectedList = new LinkedList<Object>();
	
	public GameManager(Game game) {
		this.game = game;
		grid = new Grid();
		handler = new Handler(grid,this);
		hud = new HUD(this, handler, grid);
		mouseInputGame = new MouseInputGame(game,this, handler,grid,hud);
		
		new WorldGenerator(new Random().nextInt(20), handler, grid);
		game.addKeyListener(new KeyInputGame(game,this, handler,hud));
		game.addKeyListener(hud);
		game.addMouseListener(mouseInputGame);
		game.addMouseWheelListener(mouseInputGame);
		game.addMouseMotionListener(mouseInputGame);
		preGame = new PreGame(game,this,handler,grid);
		game.addMouseListener(preGame);
	}
	
	private long prevTime = System.currentTimeMillis();
	public void tick(){

		handler.tick();
		if(!preGameActive){
			hud.tick();
			long time = System.currentTimeMillis();
			if(time > prevTime+2000){//Gold income
				gold++;
				prevTime = time;
			}
		}else{
			preGame.tick();
		}
	}
	
	
	public boolean goScale = false;
	public float scale=1;
	public int camSpeed = 10;
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		camSpeed = (int) (10/(scale/2));
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
		
	    g.setColor(Color.GRAY);//Background
		g.fillRect(0, 0, grid.cellWidth*10, grid.cellHeight*10);
		g.setColor(Color.red);
		g.drawRect(0, 0, grid.cellWidth*10, grid.cellHeight*10);
		grid.render(g);	
		handler.render(g);
		if(hud.selectedBlock != null){
			hud.selectedBlock.render(g);
		}

		g2d.translate(-camX, -camY);//end cam
		g2d.scale(1/scale, 1/scale);//end scale
		if(!preGameActive){
			hud.render(g);			
		}else{
			preGame.render(g);
		}
	}

}
