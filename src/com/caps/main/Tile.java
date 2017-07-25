package com.caps.main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.caps.objects.Block;

public class Tile {
	public int x,y;
	public Grid grid;
	public Rectangle bounds;
	public LinkedList<Block> blockList = new LinkedList<Block>();

	private boolean isWall = false;
	
	public Tile(int x, int y, Grid grid) {
		this.x = x;
		this.y = y;
		this.grid = grid;
		bounds = new Rectangle(x, y, 20, 20);
	}
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		if(!blockList.isEmpty()){
			for (Block b : blockList) {
				b.render(g2d);
			}
		}
		
		/*if (ghostBlock != null){
			Composite old = g2d.getComposite();
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)0.4));
			ghostBlock.render(g);
			g2d.setComposite(old);
		}*/

	}
	
	public LinkedList<Block> getBlockList() {
		return blockList;
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



	public Grid getGrid() {
		return grid;
	}



	public void setGrid(Grid grid) {
		this.grid = grid;
	}



	public Rectangle getBounds() {
		return bounds;
	}



	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
}
