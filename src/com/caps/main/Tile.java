package com.caps.main;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.caps.objects.Block;

public class Tile {
	public int x,y;
	public Grid grid;
	public Rectangle bounds;
	public Block ghostBlock;
	public Block Block[];
	
	public Tile(int x, int y, Grid grid) {
		this.x = x;
		this.y = y;
		this.grid = grid;
		bounds = new Rectangle(x, y, 20, 20);
	}


	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		if (ghostBlock != null){
			Composite old = g2d.getComposite();
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)0.4));
			ghostBlock.render(g);
			g2d.setComposite(old);
		}
		/*for (Object object : Block) {
			((Rectangle) object).getX();
		}*/
	}



	public Object getGhostBlock() {
		return ghostBlock;
	}



	public void setGhostBlock(Block ghostBlock) {
		this.ghostBlock = ghostBlock;
	}
	
	public Block[] getBlock() {
		return Block;
	}



	public void setBlock(Block[] block) {
		Block = block;
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
