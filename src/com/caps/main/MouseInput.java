package com.caps.main;

import java.awt.Graphics;
import java.awt.Point;
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
		Point mousePoint = new Point(mx, my);
		Sound.click.play();
		if (game.gameState == STATE.Menu){
			for (Button b : handler.menuButton) {
				if (b.getBounds().contains(mousePoint)){
					b.click();
					break;
				}
			}
		}
	}
	
	public void mouseMoved(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		Point mousePoint = new Point(mx, my);
		if (game.gameState == STATE.Menu){
			for (Button b : handler.menuButton) {
				if (b.getBounds().contains(mousePoint)){
					b.setHighlighted(true);
				}else{
					b.setHighlighted(false);
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
