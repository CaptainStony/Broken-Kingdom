package com.caps.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.caps.main.GridCell;

public class Colonist extends Object{
	
	public LinkedList<GridCell> path = new LinkedList<GridCell>();
	public LinkedList<GridCell> closedList = new LinkedList<GridCell>();
	public LinkedList<GridCell> openList = new LinkedList<GridCell>();
	
	public Colonist(float x, float y) {
		super(x, y);
		
	}

	
	@Override
	public void tick() {
		x += velX;
		y += velY;
		if (!path.isEmpty()){
			followPath();
		}
	}
	private void followPath(){
		Point cellP = new Point(path.getFirst().getX()+5, path.getFirst().getY()+5);
		gotoGridCell(path.getFirst(), this);
		if(getBoundsTotal().contains(cellP)){
			path.removeFirst();
			if(path.isEmpty()){
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
	
	public void render(Graphics g) {

		g.setColor(Color.yellow);
		g.fillRect((int)x, (int)y, 20, 20);

	}
	@Override
	public Rectangle getBoundsTotal() {
		return new Rectangle((int)x, (int)y, 20, 20);

	}

}
