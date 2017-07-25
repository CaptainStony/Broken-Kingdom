package com.caps.objects;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Object {

	public float x, y;
	public float velX;
	public float velY;
	public Rectangle bounds;
	public float health;

	public Object(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g) ;
	public abstract Rectangle getBoundsTotal();
	
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

}
