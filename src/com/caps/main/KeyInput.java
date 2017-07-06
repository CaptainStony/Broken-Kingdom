package com.caps.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyInput extends KeyAdapter{

	private Game game;
	private Handler handler;
	public KeyInput(Game game,Handler handler){
		this.game = game;
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
	}
		
	
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
	}
}
