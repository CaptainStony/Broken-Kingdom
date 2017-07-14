package com.caps.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class HUD extends KeyAdapter{

	private GameManager gameManager;
	public HUD(GameManager gameManager){
		this.gameManager = gameManager;
	}
	
	public void tick(){
		
	}

	public void render(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(10, 620, 60, 60);
	}
	
	public boolean[] keyPress = {false,false,false,false};
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_ESCAPE) System.exit(0);
		if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) keyPress[0] = true;
		if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) keyPress[1] = true;
		if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) keyPress[2] = true;
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) keyPress[3] = true;

	}
	
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) keyPress[0] = false;
		if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) keyPress[1] = false;
		if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) keyPress[2] = false;
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) keyPress[3] = false;
		
	}
	
}
