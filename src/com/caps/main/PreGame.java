package com.caps.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.caps.objects.Flag;

public class PreGame extends MouseAdapter{
	
	private Game game;
	private GameManager gameManager;
	private Handler handler;
	private Grid grid;
	
	private Image flag = null;
	
	public PreGame(Game game, GameManager gameManager, Handler handler ,Grid grid){
		this.game = game;
		this.gameManager = gameManager;
		this.handler = handler;
		this.grid = grid;
		try {
			flag = ImageIO.read(this.getClass().getResource("/flag.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void tick(){
		
		
	}
	
	public void render(Graphics g){
		g.setColor(Color.BLACK);
    	g.setFont(new Font("OCR A Extended", Font.BOLD, 30)); 
		g.drawString("Welcome To Broken Kindom", 400, 100);
    	g.setFont(new Font("OCR A Extended", Font.BOLD, 20)); 
    	g.drawString("Click a location to start your kingdom!", 380, 150);
    	g.drawImage(flag, 900, 50,150,150,null);
    	g.drawImage(flag, 360, 50,-150,150,null);

	}
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		int worldX = (int) ((mx/gameManager.scale - gameManager.camX));
		int worldY = (int) ((my/gameManager.scale - gameManager.camY));
		Tile t = grid.cordsToTile(worldX, worldY);
		if(!t.isWall()) {
			handler.addObject(new Flag(t.getX(), t.getY() ,grid,handler,gameManager));
			Sound.gameStart.play();
			game.removeMouseListener(this);
			gameManager.preGameActive = false;	
		}
	}
}
