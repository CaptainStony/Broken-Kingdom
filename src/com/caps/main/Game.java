package com.caps.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;




public class Game extends Canvas implements Runnable{
	public static STATE gameState = STATE.Menu;

	public enum STATE{
		Game,
		Menu,
		InGameMenu,
	};

	private static final long serialVersionUID = 1L;

	protected static final int WIDTH = 1280;
	protected static final int HEIGHT = 720;
	
	public static final boolean DEBUG = false;
	
	private Thread thread;
	private boolean running = false;
	private MouseInput mouseinput;
	private Menu menu;
	public GameManager gamemanager;
	public int Frames = 0;
//	private Random r = new Random();

	
	public Game(){
		menu = new Menu(this);
		mouseinput = new MouseInput(menu);
		this.addMouseMotionListener(mouseinput);
		this.addMouseListener(mouseinput);
		this.addMouseWheelListener(mouseinput);
		new Window(WIDTH, HEIGHT, "Broken Kingdom", this);
	}
	
	
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
		
	}
	public synchronized void stop(){
		try{
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run (){
		//GAME LOOP 
		  
	      this.requestFocus();
	      long lastTime = System.nanoTime();
	      double amountofTicks = 60.0;
	      double ns = 1000000000 / amountofTicks;
	      double delta = 0;
	      long timer = System.currentTimeMillis();
	      int frames = 0;
	      while(running) {
	              long now = System.nanoTime();
	              delta += (now - lastTime) / ns;
	              lastTime = now;
	              while(delta >= 1) {
	                      tick();
	                      delta--;
	              }
	              if(running)
	                      render();
	              frames++;
	             
	              if(System.currentTimeMillis() - timer > 1000) {
	                      timer += 1000;
	                      //System.out.println("FPS: " + frames);
	                      Frames = frames;
	                      frames = 0;
	              }
	             
	      }
	      stop();
	}

	private void tick(){
		mouseinput.tick();
		if(gameState == STATE.Game || gameState == STATE.InGameMenu){
			gamemanager.tick();
		}else if(gameState == STATE.Menu){
			menu.tick();
		}
	
	}
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		if (gameState == STATE.Game || gameState == STATE.InGameMenu){
			gamemanager.render(g);
		}else if (gameState == STATE.Menu){
		    g.setColor(Color.GRAY);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			menu.render(g);
		}
		mouseinput.render(g);

		g.dispose();
		bs.show();
	}

	public static void main(String args[]){
			new Game();
			
	}
}