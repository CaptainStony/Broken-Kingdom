package com.caps.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import com.caps.ButtonMenu.Back;
import com.caps.ButtonMenu.Credits;
import com.caps.ButtonMenu.IButtonFunctions;
import com.caps.ButtonMenu.Options;
import com.caps.ButtonMenu.PlayMusic;
import com.caps.ButtonMenu.StartGame;

public class Menu {
	
	public LinkedList<Button> menuButton = new LinkedList<Button>();
	public LinkedList<Button> optionButtons = new LinkedList<Button>();
	
	public static STATE menuState = STATE.None;
	private Image backgroundImage = null;
	
	public static enum STATE {
		None,
		Options,
		Credits,

	};
	
	public Menu(Game game) {
		int spacing = 10;
		addMenuButton(Game.WIDTH / 2 - 250, Game.HEIGHT / 2 - 110, 500, 30, "Start Game", new StartGame(game),game);
		addMenuButton(menuButton.getFirst().getX(), menuButton.getFirst().getY() + (30 + spacing), 500, 30, "Options", new Options(), game); //button height + spacing of 10px
		addMenuButton(menuButton.getFirst().getX(), menuButton.get(1).getY() + (30 + spacing), 500, 30, "Credits", new Credits(game), game);
		addOptionButton(50, 50, 250, 30, "Sound: unmuted", new PlayMusic(this), game);
		addOptionButton(50, Game.HEIGHT - 100, 250, 30, "Return", new Back(), game);
		try {
			backgroundImage = ImageIO.read(this.getClass().getResource("/menu/kingdom.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sound.backMusic.loop();
	}
	
	public void tick(){
		switch(menuState){
		case Credits:
			for (int i = 0; i < menuButton.size(); i++) {
				Button tempButton = menuButton.get(i);
				tempButton.tick();
			}
			break;
		case None:
			for (int i = 0; i < menuButton.size(); i++) {
				Button tempButton = menuButton.get(i);
				tempButton.tick();
			}
			break;
		case Options:
			for (int i = 0; i < optionButtons.size(); i++) {
				Button tmp = optionButtons.get(i);
				tmp.tick();
			}
			break;
		default:
			for (int i = 0; i < menuButton.size(); i++) {
				Button tempButton = menuButton.get(i);
				tempButton.tick();
			}
			break;
		}
	}

	public void render(Graphics g){
		if(backgroundImage != null){
			g.drawImage(backgroundImage,0,0,Game.WIDTH,Game.HEIGHT,null);
		}
		switch(menuState){
		case Credits:
			g.setColor(Color.black);
	    	Font orgfont = g.getFont();
	    	g.setFont(new Font("TimesRoman", Font.PLAIN, 50)); 
	    	g.drawString("Credits:", 800, 50);
	    	g.setFont(new Font("OCR A Extended", Font.PLAIN, 30)); 
	    	g.setColor(Color.green);
	    	g.drawString("CaptainStony", 800, 90);
	    	g.drawString("ThaFartKnight", 800, 120);
	    	g.setFont(orgfont);
	    	for (int i = 0; i < menuButton.size(); i++) {
				Button tempButton = menuButton.get(i);
				tempButton.render(g);
			}
			break;
		case None:
			for (int i = 0; i < menuButton.size(); i++) {
				Button tempButton = menuButton.get(i);
				tempButton.render(g);
			}
			break;
		case Options:
			for (int i = 0; i < optionButtons.size(); i++) {
				Button tempButton = optionButtons.get(i);
				tempButton.render(g);
			}
			break;
		}
		
	}
	private void addMenuButton(int x, int y,int width, int height, String text, IButtonFunctions ibf, Game game){
		addMenuButton(new Button(x, y, width, height, text, ibf));
	}
	
	public void addMenuButton(Button button){
		menuButton.add(button);
	}
	
	private void addOptionButton(int x, int y,int width, int height, String text, IButtonFunctions ibf, Game game){
		addOptionButton(new Button(x, y, width, height, text, ibf));
	}
	
	public void addOptionButton(Button button){
		optionButtons.add(button);
	}

	public void removeButton(Button button){
		menuButton.remove(button);
	}
}
