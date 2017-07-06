package com.caps.main;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.caps.main.Game.STATE;


public class MouseInput extends MouseAdapter{
	private Game game;
	
	public MouseInput(Game game){		
		this.game = game;
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		if (game.gameState == STATE.Menu){
			
		}
	}
	
	public void mouseReleased(MouseEvent e){
		
	}
	public void tick(){
		
	}

	public void render(Graphics g){

	}

}
