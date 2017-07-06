package com.caps.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyInput extends KeyAdapter{

	private Game game;
	
	public KeyInput(Game game){
		this.game = game;

	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
	}
		
	
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
	}
}
