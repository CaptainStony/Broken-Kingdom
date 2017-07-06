package com.caps.main;

import java.awt.Graphics;

import com.caps.cmd.Template;
import com.caps.cmd.IButtonFunctions;

public class Menu {
	private Game game;
	private Handler handler;
	private IButtonFunctions ibf;
	public Menu(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
		addButton(50, 50, 500, 30, "Memes", new Template(game),game);
	}
	
	public void tick(){
		
	}

	public void render(Graphics g){
	    
	}
	private void addButton(int x, int y,int width, int height, String text, IButtonFunctions ibf, Game game){
		Button button = new Button(x, y, width, height, text, ibf, game);
		handler.addButton(button);
		button.click();
	}
}
