package com.caps.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import com.caps.ButtonMenu.Credits;
import com.caps.ButtonMenu.IButtonFunctions;
import com.caps.ButtonMenu.Options;
import com.caps.ButtonMenu.StartGame;

public class Menu {
	private Game game;
	public STATE menuState = STATE.None;

	
	public LinkedList<Button> menuButton = new LinkedList<Button>();
	
	private Image backgroundImage = null;
	
	public enum STATE{
		None,
		Options,
		Credits,

	};
	
	public Menu(Game game) {
		this.game = game;
		addButton(50, 50, 500, 30, "Start Game", new StartGame(game),game);
		addButton(50, 90, 500, 30, "Options", new Options(this),game);
		addButton(50, 130, 500, 30, "Credits", new Credits(game,this),game);
		
		try {
			backgroundImage = ImageIO.read(this.getClass().getResource("/menu/kingdom.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sound.backMusic.loop();

	}
	
	public void tick(){
		for (int i = 0; i < menuButton.size(); i++) {
			Button tempButton = menuButton.get(i);
			tempButton.tick();
		}	
	
	}

	public void render(Graphics g){
		g.drawImage(backgroundImage,0,0,Game.WIDTH,Game.HEIGHT,null);
	    if (menuState == STATE.Options){
	    	g.setColor(Color.blue);
	    	g.drawRect(90, 90, 60, 60);
	    }else if (menuState == STATE.Credits){
	    	g.setColor(Color.black);
	    	Font orgfont = g.getFont();
	    	g.setFont(new Font("TimesRoman", Font.PLAIN, 50)); 
	    	g.drawString("Credits:", 800, 50);
	    	g.setFont(new Font("OCR A Extended", Font.PLAIN, 30)); 
	    	g.setColor(Color.green);
	    	g.drawString("ThaFartKnight", 800, 90);
	    	g.drawString("CaptainStony", 800, 120);
	    	g.setFont(orgfont);
	    }
		for (int i = 0; i < menuButton.size(); i++) {
			Button tempButton = menuButton.get(i);
			tempButton.render(g);
		}
	}
	private void addButton(int x, int y,int width, int height, String text, IButtonFunctions ibf, Game game){
		Button button = new Button(x, y, width, height, text, ibf, game);
		addButton(button);
	}
	
	public void addButton(Button button){
		menuButton.add(button);
	}

	public void removeButton(Button button){
		menuButton.remove(button);
	}
}
