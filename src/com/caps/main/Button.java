package com.caps.main;

import java.awt.Color;
import java.awt.Graphics;

import com.caps.cmd.IButtonFunctions;

public class Button {
	private Game game; // Sosi khuy
	private int x,y,width,height;
	private String text;
	private IButtonFunctions func; //skeeer
	public Button(int x, int y, int width, int height,String text, IButtonFunctions ibf, Game game) {
		this.game = game;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.func = ibf;
	}
	
	public void tick(){
		
	}
	public void render(Graphics g){
		g.setColor(Color.black);
		g.fillRect(x, y, width, height);
		g.setColor(Color.white);
		g.drawString(text, (int) (x+width/2-(text.length()*3)), y+height/2);
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
}
