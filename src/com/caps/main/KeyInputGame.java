package com.caps.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyInputGame extends KeyAdapter{

	private GameManager gameManager;
	
	public KeyInputGame(Game game,GameManager gameManager){
		this.gameManager = gameManager;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_Q) {
			gameManager.GAMETIME += 200;
		}
	}
		
	
	public void keyReleased(KeyEvent e){
		//int key = e.getKeyCode();

	}
}
