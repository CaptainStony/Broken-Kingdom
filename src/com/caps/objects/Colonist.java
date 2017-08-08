package com.caps.objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.caps.ButtonHUD.GameButton;
import com.caps.ButtonHUD.SetupWorkCamp;
import com.caps.main.GameManager;
import com.caps.main.Grid;
import com.caps.main.GridCell;
import com.caps.main.Handler;

public class Colonist extends GameObject {
	
	private Grid grid;
	private Handler handler;
	public LinkedList<GameButton> colonistButtons = new LinkedList<GameButton>();
	
	
	public Colonist(float x, float y, Grid grid, Handler handler, GameManager gameManager) {
		super(x, y);
		this.grid = grid;
		this.handler = handler;
		health = 50;
		colonistButtons.add(new GameButton(120, 625, 130, 50, "Make Work Camp", new SetupWorkCamp(handler, grid, gameManager, this)));
		gridUpdate();

	}

	LinkedList<GridCell> prevC = new LinkedList<GridCell>();

	public void tick() {
		x += velX;
		y += velY;
		if(health <= 0) handler.removeObject(this);

		if (!path.isEmpty()){
			followPath();
			gridUpdate();
		}
	
	}
	


	public void render(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect((int)x, (int)y, 20, 20);
		g.setColor(Color.black);
    	g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
		g.drawString("C", (int)x+4, (int)y+16);
		showPath(g);

	}

	public Rectangle getBoundsTotal() {
		return new Rectangle((int)x, (int)y, 20, 20);

	}

	private void gridUpdate(){
		for (GridCell pc : prevC) {
			pc.objectList.remove(this);
		}
		prevC.clear();
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				GridCell c = grid.cordsToGridCells((int)x+i, (int)y+j);
				if(!c.objectList.contains(this)){
					c.objectList.add(this);
					prevC.add(c);
				}
			}
		}
	}
	
	private void followPath(){
		Point cellP = new Point(path.getFirst().getX()+5, path.getFirst().getY()+5);
		gotoGridCell(path.getFirst(), this);
		if(getBoundsTotal().contains(cellP)){
			path.removeFirst();
			if(path.isEmpty()){
				/*x = grid.cordsToTile((int)x, (int)y).getX();
				y = grid.cordsToTile((int)x, (int)y).getY();*/
				velX = 0;
				velY = 0;
			}
		}
		
	}
	
	private void gotoGridCell(GridCell gridcell,Colonist obj){
		float difX = gridcell.getX() - obj.getX();
		float difY = gridcell.getY() - obj.getY();
		float angl = (float) Math.atan(difY/difX);
		int baseSpeed = 2;
		
		if(difX>0 && difY<0 || difX>0 && difY>0){
			obj.velX = (float) (baseSpeed * Math.cos(angl));
			obj.velY = (float) (baseSpeed * Math.sin(angl));
			
		}else if (difX<0 && difY<0 || difX<0 && difY>0){
			obj.velX = (float) -(baseSpeed * Math.cos(angl));
			obj.velY = (float) -(baseSpeed * Math.sin(angl));
		}
	}

}
