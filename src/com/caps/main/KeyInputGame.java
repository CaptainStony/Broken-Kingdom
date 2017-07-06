package com.caps.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyInputGame extends KeyAdapter{

	private Game game;
	private Handler handler;
	private GameManager gameManager;
	public KeyInputGame(Game game,GameManager gameManager,Handler handler){
		this.game = game;
		this.handler = handler;
		this.gameManager = gameManager;
	}
	
	private boolean[] keyPress = {false,false,false,false};
	private int camSpeed = 10;
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_ESCAPE) System.exit(0);
		if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) keyPress[0] = true;
		if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) keyPress[1] = true;
		if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) keyPress[2] = true;
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) keyPress[3] = true;
		if(keyPress[2] == true){
			gameManager.camX+=camSpeed;
		}
		if(keyPress[3] == true){
			gameManager.camX-=camSpeed;
		}
		if(keyPress[0] == true){
			gameManager.camY+=camSpeed;
		}
		if(keyPress[1] == true){
			gameManager.camY-=camSpeed;
		}	

	}
		
	
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) keyPress[0] = false;
		if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) keyPress[1] = false;
		if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) keyPress[2] = false;
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) keyPress[3] = false;
	}
}
