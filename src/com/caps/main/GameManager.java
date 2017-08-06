package com.caps.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.Random;

import com.caps.main.Event.EVENT;
import com.caps.main.Game.STATE;
import com.caps.objects.GameObject;
import com.caps.objects.Zombie;

public class GameManager {

	private Game game;
	private Handler handler;
	private Grid grid;
	private HUD hud;
	private MouseInputGame mouseInputGame;
	private PreGame preGame;
	private InGameMenu inGameMenu;
	
	
	public int camX=0,camY=0;
	public boolean preGameActive = true;

	public int STONE=99999,GOLD=99999,IRON=99999;
	public int GAMETIME=0; // night start=4500, night end=7000
	public boolean DAY;
	
	public LinkedList<GameObject> selectedList = new LinkedList<GameObject>();
	
	public GameManager(Game game) {
		this.game = game;
		grid = new Grid();
		handler = new Handler(grid,this);
		hud = new HUD(this, handler, grid);
		inGameMenu = new InGameMenu();
		mouseInputGame = new MouseInputGame(game,this, handler,grid,hud,inGameMenu);
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
	
	
	public boolean goScale = false;
	public float scale=1;
	public int camSpeed = 10;
	public void tick(){
		camSpeed = (int) (10/(scale/2));
		handler.tick();
		if(Game.gameState == STATE.Game){
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
			if(!preGameActive){
				hud.tick();
				//gametime manager
				//night start=4500, night end=7000
				if(GAMETIME >= 4500){
					if(GAMETIME == 4500) {//1 time run
						new Event(this, handler, grid,hud, EVENT.ZombieAttack);
					}
					DAY = false;
				}else{
					DAY = true;
				}
				if(GAMETIME > 7000){
					GAMETIME = 0;
				}else{
					GAMETIME++;
				}
				
				long time = System.currentTimeMillis();
				if(time > prevTime+2000){//Gold income
					GOLD++;
					prevTime = time;
				}
			}else{
				preGame.tick();
			}
		}else if(Game.gameState == STATE.InGameMenu){
			inGameMenu.tick();
		}
	}
	
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;		
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
		if(Game.gameState == STATE.InGameMenu){
			inGameMenu.render(g);
		}
	}

}
