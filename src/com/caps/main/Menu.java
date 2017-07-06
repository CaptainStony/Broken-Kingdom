package com.caps.main;

import java.awt.Graphics;

public class Menu {
	private Game game;
	private Handler handler;
	private IButtonFunctions ibf;
	public Menu(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
		addButton(50, 50, 500, 30, "Memes", "mememee",game);
	}
	
	public void tick(){
		
	}

	public void render(Graphics g){
	    
	}
	private void addButton(int x, int y,int width, int height, String text, String command, Game game){
		Button button = new Button(x, y, width, height, text, command, game);
		handler.addButton(button);
	}
}
