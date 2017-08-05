package com.caps.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.caps.main.GridCell;

public abstract class GameObject {

	public float x, y;
	public float velX;
	public float velY;
	public Rectangle bounds;
	public float health;

	public GameObject(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g) ;
	public abstract Rectangle getBoundsTotal();
	public LinkedList<GridCell> path = new LinkedList<GridCell>();

	protected void showPath(Graphics g){
		if(!path.isEmpty()){
			g.setColor(Color.darkGray);
			for (int i = 0; i < path.size(); i++) {
				if(i != 0 && i != path.size()){
					g.drawLine(path.get(i).getX()+5, path.get(i).getY()+5, path.get(i-1).getX()+5, path.get(i-1).getY()+5);					
				}
			}
		}
	}
	
	public void setX(float x){
		this.x = x;
	}
	
	public void setY(float y){
		this.y = y;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}
	
	public void setVelX(float velX){
		this.velX = velX;
	}
	
	public void setVelY(float velY){
		this.velY= velY;
	}
	
	public float getVelX(){
		return velX;
	}
	
	public float getVelY(){
		return velY;
	}
	public LinkedList<GridCell> getPath() {
		return path;
	}

	public void setPath(LinkedList<GridCell> path) {
		this.path = path;
	}
}
