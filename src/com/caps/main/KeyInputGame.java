package com.caps.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyInputGame extends KeyAdapter{

	private Game game;
	private Handler handler;
	private GameManager gameManager;
	private HUD hud;
	
	public KeyInputGame(Game game,GameManager gameManager,Handler handler, HUD hud){
		this.game = game;
		this.handler = handler;
		this.gameManager = gameManager;
		this.hud = hud;
	}
	
	public void keyPressed(KeyEvent e){

	}
		
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();

	}
}
