package com.caps.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.caps.cmd.StartGame;
import com.caps.main.Game.STATE;


public class MouseInput extends MouseAdapter{
	private Game game;
	private Handler handler;
	public MouseInput(Game game, Handler handler){		
		this.game = game;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		Rectangle mouseRect = new Rectangle(mx, my, 1, 1);
		if (game.gameState == STATE.Menu){
			for (Button b : handler.menuButton) {
				if(b.getFunctionClass() == StartGame.class){
					if (mouseRect.intersects(b.getBounds())){
						b.click();
						break;
					}
				}
			}
		}
	}
	
	public void mouseReleased(MouseEvent e){
		
	}
	public void tick(){
		
	}

	public void render(Graphics g){

	}

}
