package com.caps.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.caps.ButtonMenu.IButtonFunctions;

public class Button {
	private int x,y,width,height;
	private String text;
	private IButtonFunctions func;
	private Rectangle bounds;

	private Boolean highlighted = false;
	
	public Button(int x, int y, int width, int height,String text, IButtonFunctions ibf) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.func = ibf;
		bounds = new Rectangle(x, y, width, height);
	}

	public void tick(){
		
	}
	
	public void render(Graphics g){
		g.setColor(Color.black);
		g.fillRect(x, y, width, height);
		g.setColor(Color.white);
		g.drawString(text, (int) (x+width/2-(text.length()*3)), y+height/2);
		
		if (highlighted){
			g.setColor(Color.white);
			g.drawRect(x, y, width, height);
		}
			
	}
	
	@SuppressWarnings("rawtypes")
	public Class getFunctionClass(){
		return func.getClass();
	}
	
	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void click(){
		func.execute();
	}
	public Boolean getHighlighted() {
		return highlighted;
	}

	public void setHighlighted(Boolean highlighted) {
		this.highlighted = highlighted;
	}
}
