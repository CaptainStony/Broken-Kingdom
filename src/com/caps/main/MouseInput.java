package com.caps.main;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelListener;

import com.caps.ButtonMenu.Button;
import com.caps.main.Game.STATE;


public class MouseInput extends MouseAdapter implements MouseWheelListener{
	private Menu menu;
	public MouseInput(Menu menu){		
		this.menu = menu;
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		Point mousePoint = new Point(mx, my);
		Sound.click.play();
		if (Game.gameState == STATE.Menu){
			switch(Menu.menuState){
			case None:
			case Credits:
				for (Button b : menu.menuButton) {
					if (b.getBounds().contains(mousePoint)){
						b.click();
						break;
					}
				}
				break;
			case Options:
				for (Button b : menu.optionButtons) {
					if (b.getBounds().contains(mousePoint)){
						b.click();
						break;
					}
				}
				break;
			}
			
		}
	}
	
	public void mouseMoved(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		Point mousePoint = new Point(mx, my);
		if (Game.gameState == STATE.Menu){
			for (Button b : menu.menuButton) {
				if (b.getBounds().contains(mousePoint)){
					b.setHighlighted(true);
					break;
				}else{
					b.setHighlighted(false);
				}
			}
			for (Button b : menu.optionButtons) {
				if (b.getBounds().contains(mousePoint)){
					b.setHighlighted(true);
					break;
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
