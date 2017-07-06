package com.caps.main;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelListener;

import com.caps.cmd.StartGame;
import com.caps.main.Game.STATE;
import com.caps.objects.Colonist;


public class MouseInput extends MouseAdapter implements MouseWheelListener{
	private Game game;
	private Menu menu;
	public MouseInput(Game game, Menu menu){		
		this.game = game;
		this.menu = menu;
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		Point mousePoint = new Point(mx, my);
		Sound.click.play();
		if (game.gameState == STATE.Menu){
			for (Button b : menu.menuButton) {
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
			for (Button b : menu.menuButton) {
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
