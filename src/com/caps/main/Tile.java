package com.caps.main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.caps.objects.Block;
import com.caps.objects.BoundWall;
import com.caps.objects.GoldOre;
import com.caps.objects.IronOre;
import com.caps.objects.Stone;
import com.caps.objects.StoneWall;

public class Tile {
	public int x,y;
	public Grid grid;
	public Rectangle bounds;
	public LinkedList<Block> blockList = new LinkedList<Block>();
	
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
	}
	
	public boolean isWall() {
		for (Block block : blockList) {
			if(block instanceof IronOre || block instanceof GoldOre || block instanceof Stone || block instanceof StoneWall || block instanceof BoundWall) {
				return true;
			}
		}
		return false;
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
