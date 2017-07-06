package com.caps.main;

import java.awt.Graphics;
import java.util.LinkedList;

import com.caps.main.Game.STATE;
import com.caps.objects.Colonist;
import com.caps.objects.Object;

public class Handler {

	LinkedList<Button> menuButton = new LinkedList<Button>();
	LinkedList<Object> Colonist = new LinkedList<Object>();

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
	
	public void addObject(Object object){
		if(object instanceof Colonist){
			Colonist.add(object);
		}
	}
	
	public void removeObject(Object object){
		if(object instanceof Colonist){
			Colonist.remove(object);
		}
	}
	
	public void addButton(Button button){
		this.menuButton.add(button);
	}

	public void removeButton(Button button){
		this.menuButton.remove(button);
	}


}
