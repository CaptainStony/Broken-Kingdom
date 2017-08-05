package com.caps.objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.caps.main.GridCell;
import com.caps.main.Tile;

public abstract class Block {

	public float x, y;
	public GridCell gridLocation;
	public Tile tileLocation;
	public int GoldNeededToBuild;
	public int StoneNeededToBuild;
	public int IronNeededToBuild;

	public Block(float x, float y){
		this.x = x;
		this.y = y;
	}

	public abstract void tick();
	public abstract void render(Graphics g) ;
	public abstract Rectangle getBoundsTotal();

	
	public GridCell getGridLocation() {
		return gridLocation;
	}
	public void setGridLocation(GridCell gridLocation) {
		this.gridLocation = gridLocation;
	}
	
	public Tile getTileLocation() {
		return tileLocation;
	}

	public void setTileLocation(Tile tileLocation) {
		this.tileLocation = tileLocation;
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
	
	public int getGoldNeededToBuild() {
		return GoldNeededToBuild;
	}

	public void setGoldNeededToBuild(int goldNeededToBuild) {
		GoldNeededToBuild = goldNeededToBuild;
	}

	public int getStoneNeededToBuild() {
		return StoneNeededToBuild;
	}

	public void setStoneNeededToBuild(int stoneNeededToBuild) {
		StoneNeededToBuild = stoneNeededToBuild;
	}

	public int getIronNeededToBuild() {
		return IronNeededToBuild;
	}

	public void setIronNeededToBuild(int ironNeededToBuild) {
		IronNeededToBuild = ironNeededToBuild;
	}

}
