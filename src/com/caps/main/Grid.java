package com.caps.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Grid {

	LinkedList<GridCell> gridCell = new LinkedList<GridCell>();
	LinkedList<Tile> tile = new LinkedList<Tile>();
	public int cellWidth = 100, cellHeight = 60;	
	public Grid() {
		generateGrid(cellWidth,cellHeight);
		generateTiles(cellWidth,cellHeight);
	}
	
	private void generateGrid(int a, int b){
		for (int i = 0; i < a; i++) {
			for (int j = 0; j < b; j++) {
				gridCell.add(new GridCell(i*10, j*10, this));
			}
		}
	}
	
	private void generateTiles(int a, int b){
		a = a/2;
		b = b/2;
		for (int i = 0; i < a; i++) {
			for (int j = 0; j < b; j++) {
				tile.add(new Tile(i*20, j*20, this));
			}
		}
	}
	
	public void render(Graphics g){
		/*for (Tile tile : tile) {
			tile.render(g);
		}*/
		for (GridCell cell : gridCell) {
			cell.render(g);
		}
	}
}
