package com.caps.objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

import com.caps.ButtonHUD.GameButton;
import com.caps.ButtonHUD.SpawnKnight;
import com.caps.main.GameManager;
import com.caps.main.Grid;
import com.caps.main.GridCell;
import com.caps.main.Handler;

public class Flag extends GameObject{
	
	public LinkedList<GameButton> flagButtons = new LinkedList<GameButton>();
	public GridCell waypoint;
	private GameManager gameManager;
	
	public Flag(float x, float y ,Grid grid,Handler handler, GameManager gameManager) {
		super(x, y);
		health = 20;
		this.gameManager = gameManager;
		//Moet beter
		Colonist c = new Colonist(x, y,grid,handler, gameManager);
		c.setPath(grid.calculatePath(grid.cordsToGridCells((int)x, (int)y), grid.cordsToGridCells((int)x+randInt(30, 60), (int)y+randInt(30, 60))));
		handler.addObject(c);
		
		Colonist c2 = new Colonist(x, y,grid,handler, gameManager);
		c2.setPath(grid.calculatePath(grid.cordsToGridCells((int)x, (int)y), grid.cordsToGridCells((int)x-randInt(30, 60), (int)y-randInt(30, 60))));
		handler.addObject(c2);	
		
		Colonist c3 = new Colonist(x, y,grid,handler, gameManager);
		c3.setPath(grid.calculatePath(grid.cordsToGridCells((int)x, (int)y), grid.cordsToGridCells((int)x+randInt(30, 60), (int)y-randInt(30, 60))));
		handler.addObject(c3);	
		
		Colonist c4 = new Colonist(x, y,grid,handler, gameManager);
		c4.setPath(grid.calculatePath(grid.cordsToGridCells((int)x, (int)y), grid.cordsToGridCells((int)x-randInt(30, 60), (int)y+randInt(30, 60))));
		handler.addObject(c4);
		
		grid.cordsToGridCells((int)x, (int)y).objectList.add(this);
		grid.cordsToGridCells((int)x+10, (int)y).objectList.add(this);
		grid.cordsToGridCells((int)x+10, (int)y+10).objectList.add(this);
		grid.cordsToGridCells((int)x, (int)y+10).objectList.add(this);
		
		GridCell startCell = grid.cordsToGridCells((int)x, (int)y);
		waypoint = grid.cordsToGridCells((int)x, (int)y+60);
		
		flagButtons.add(new GameButton(70, 625, 50, 50, "Knight", new SpawnKnight(handler, grid, startCell)));
		
		
	}



	private  int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	@Override
	public void tick() {
		
	}
	
	public void render(Graphics g) {

		g.setColor(Color.green);
		g.fillRect((int)x, (int)y, 20, 20);
		g.setColor(Color.black);
    	g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
		g.drawString("F", (int)x+5, (int)y+16);
		
		if(gameManager.selectedList.contains(this)){
			g.setColor(Color.pink);
			g.fillRect(waypoint.getX()+2, waypoint.getY()+2, 6, 6);	
		}

	}
	@Override
	public Rectangle getBoundsTotal() {
		return new Rectangle((int)x, (int)y, 20, 20);

	}

	public GridCell getWaypoint() {
		return waypoint;
	}


	public void setWaypoint(GridCell waypoint) {
		this.waypoint = waypoint;
	}
}
