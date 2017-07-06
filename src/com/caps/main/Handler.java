package com.caps.main;

import java.awt.Graphics;
import java.util.LinkedList;

import com.caps.main.Game.STATE;

public class Handler {

	LinkedList<Button> menuButton = new LinkedList<Button>();
	
	public void tick(){
		if (Game.gameState == STATE.Menu){
			for (int i = 0; i < menuButton.size(); i++) {
				Button tempButton = menuButton.get(i);
				tempButton.tick();
			}	
		}
	}
	
	public void render(Graphics g){
		if (Game.gameState == STATE.Menu){
			for (int i = 0; i < menuButton.size(); i++) {
				Button tempButton = menuButton.get(i);
				tempButton.render(g);
			}
		}
	}
	 
	public void addButton(Button button){
		this.menuButton.add(button);
	}

	public void removeButton(Button button){
		this.menuButton.remove(button);
	}


}
