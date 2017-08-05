package com.caps.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;

import com.caps.ButtonInGameMenu.EndGame;
import com.caps.ButtonInGameMenu.InGameMenuButton;

public class InGameMenu {

	public STATE menuState = STATE.Start;

	
	LinkedList<InGameMenuButton> startMenuButtons = new LinkedList<InGameMenuButton>();
		
	public enum STATE{
		Start,
		Options,

	};
	
	public InGameMenu() {
		startMenuButtons.add(new InGameMenuButton(24, 50, 270, 24, "Quit Game", new EndGame()));
	}
	
	public void tick(){
		if(menuState == STATE.Start){
			for (int i = 0; i < startMenuButtons.size(); i++) {
				InGameMenuButton tempButton = startMenuButtons.get(i);
				tempButton.tick();
			}		
		}
	
	}

	public void render(Graphics g){
	    if (menuState == STATE.Start){
	    	g.setColor(Color.black);
	    	g.fillRect(10, 10, 300, 300);
	    	g.setColor(Color.white);
	    	g.drawRect(10, 10, 300, 300);
	    	g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	    	g.drawString("1227 menu", 120, 30);
	    	g.drawLine(10, 34, 310, 34);
	    	
			for (int i = 0; i < startMenuButtons.size(); i++) {
				InGameMenuButton tempButton = startMenuButtons.get(i);
				tempButton.render(g);
			}
	    }
	}
	
	public void addButton(InGameMenuButton button, STATE state){
		if(state == STATE.Start){
			startMenuButtons.add(button);
		}
	}
}
