package com.caps.main;

import java.awt.event.KeyAdapter;


public class KeyInput extends KeyAdapter{

	private Game game;
	
	public KeyInput(Game game){
		this.game = game;

	}
	
	public void keyPressed(java.awt.event.KeyEvent e){
		int key = e.getKeyCode();
	}
		
	
	
	public void keyReleased(java.awt.event.KeyEvent e){
		int key = e.getKeyCode();
	}
}
