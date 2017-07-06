package com.caps.objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.caps.main.GridCell;

public abstract class Object {

	protected float x, y;
	protected float velX, velY;
	protected GridCell gridLocation;

	public Object(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBoundsTotal();

	public GridCell getGridLocation() {
		//set grid loc
		return gridLocation;
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

}
