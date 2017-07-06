package com.caps.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;




public class Game extends Canvas implements Runnable{
	public static STATE gameState = STATE.Menu;

	public enum STATE{
		Game,
		Menu,

	};

	private static final long serialVersionUID = 1L;

	protected static final int WIDTH = 1280;

	protected static final int HEIGHT = 720;
	private Thread thread;
	private boolean running = false;
	private MouseInput mouseinput;
	private Menu menu;
	private Handler handler;
	private int Frames = 0;
//	private Random r = new Random();

	
	public Game(){
		handler = new Handler();
		menu = new Menu(this,handler);
		mouseinput = new MouseInput(this, handler);
		this.addMouseMotionListener(mouseinput);
		this.addMouseListener(mouseinput);
		new Window(WIDTH, HEIGHT, "skirr", this);
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
		handler.tick();
		if(gameState == STATE.Game){
			
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
	    g.setColor(Color.GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		handler.render(g);
		mouseinput.render(g);
		if (gameState == STATE.Game){
			g.setColor(Color.cyan);
			g.fillRect(60, 60, 80, 80);
		}else if (gameState == STATE.Menu){
			menu.render(g);
		}
		
		g.setFont(new Font("default", Font.PLAIN, 10));
		g.setColor(Color.white);
		g.drawString(""+Frames, 5, 20);
		
		g.dispose();
		bs.show();
	}

	public static void main(String args[]){
			new Game();
			
	}
}