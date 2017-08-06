package com.caps.main;

import java.awt.Graphics;
import java.util.LinkedList;

import com.caps.objects.Block;
import com.caps.objects.GoldOre;
import com.caps.objects.IronOre;
import com.caps.objects.Stone;
import com.caps.objects.StoneWall;

public class Grid {

	LinkedList<GridCell> gridCell = new LinkedList<GridCell>();
	LinkedList<Tile> tile = new LinkedList<Tile>();
	public int cellWidth = 700, cellHeight = 700;
	GridCell[][] world = new GridCell[cellWidth][cellHeight];
	Tile[][] worldTile = new Tile[cellWidth/2][cellHeight/2];
	
	public Grid() {
		generateGrid(cellWidth,cellHeight);
		generateTiles(cellWidth,cellHeight);
	}
	
	public void render(Graphics g){
		for (Tile tile : tile) {
			tile.render(g);				
		}
		for (GridCell cell : gridCell) { // Is for pathfinding debug
			cell.render(g);
		}
	}
	
	public GridCell cordsToGridCells(int x, int y){
		
		return world[(int)x/10][(int)y/10];
	}
	
	public Tile cordsToTile(int x, int y){
		
		return worldTile[(int)x/20][(int)y/20];
	}
	
	private void generateGrid(int a, int b){
		for (int i = 0; i < a; i++) {
			for (int j = 0; j < b; j++) {
				GridCell cell = new GridCell(i*10, j*10, this);
				world[i][j] = cell;
				gridCell.add(cell);
			}
		}
	}
	
	private void generateTiles(int a, int b){
		a = a/2;
		b = b/2;
		for (int i = 0; i < a; i++) {
			for (int j = 0; j < b; j++) {
				//tile.add(new Tile(i*20, j*20, this));
				Tile t = new Tile(i*20, j*20, this);
				tile.add(t);
				worldTile[i][j] = t;
			}
		}
	}
	
	private int calculateH(GridCell current, GridCell endCell){
		return (int) Math.sqrt(((current.getX()-endCell.getX())*(current.getX()-endCell.getX()) + ((current.getY()-endCell.getY())*(current.getY()-endCell.getY()))));
	}
	
	public int getIndexOfMin(LinkedList<Float> data) {
	    float min = Float.MAX_VALUE;
	    int index = -1;
	    for (int i = 0; i < data.size(); i++) {
	        Float f = data.get(i);
	        if (Float.compare(f.floatValue(), min) < 0) {
	            min = f.floatValue();
	            index = i;
	        }
	    }
	    return index;
	}
	
	
	public LinkedList<GridCell> calculatePathTEST(GridCell startcell, GridCell endcell){
		LinkedList<GridCell> closedList = new LinkedList<GridCell>();
		LinkedList<GridCell> openList = new LinkedList<GridCell>();
		LinkedList<GridCell> path = new LinkedList<GridCell>();
		GridCell current = startcell;
		LinkedList<Float> openListF = new LinkedList<Float>();
		
		openList.add(startcell);
		openListF.add((float) startcell.getF());
		startcell.setG(0);
		startcell.setH(calculateH(startcell, endcell));
		
		Tile startTile = cordsToTile(startcell.getX(), startcell.getY());
		Tile endTile = cordsToTile(endcell.getX(), endcell.getY());
		if(startTile.isWall() || endTile.isWall()) {
			return path;
		}
		
		for (GridCell gridCell : gridCell) {
			gridCell.render = false;
		}
		
		while(!openList.isEmpty()){	
			System.out.println(System.currentTimeMillis());
			current = openList.get(getIndexOfMin(openListF));
			System.out.println("");

			if(current == endcell){
				path.add(current);
				while(current != startcell){
					current = current.getPreviousCell();
					path.addFirst(current);
				}
				return path;
			}
			openListF.remove(openList.indexOf(current));
			openList.remove(current);
			closedList.add(current);
			LinkedList<GridCell> adjCells = current.getAvailableAdjacentCells();
			for (GridCell neighbor : adjCells) {
				int backG = neighbor.getG();
				GridCell backNeighborPrevCell = neighbor.getPreviousCell();
				if(!closedList.contains(neighbor)){
					neighbor.setPreviousCell(current);
					neighbor.setH(calculateH(neighbor, endcell));
					if(neighbor.isDiagonal(current)){
						neighbor.setG(current.getG()+14);						
					}else{
						neighbor.setG(current.getG()+10);
					}
					if(!openList.contains(neighbor)){
						openList.add(neighbor);
						openListF.add((float) neighbor.getF());
					}else{
						if(neighbor.getG() < backG){
							neighbor.render = true;
							neighbor.setG(backG);
							neighbor.setPreviousCell(backNeighborPrevCell);
						}
					}
				}
			}
			
			
		}
		return path;
	}
	
	public LinkedList<GridCell> calculatePath(GridCell startcell, GridCell endcell){
		LinkedList<GridCell> closedList = new LinkedList<GridCell>();
		LinkedList<GridCell> openList = new LinkedList<GridCell>();
		LinkedList<GridCell> path = new LinkedList<GridCell>();
		GridCell current = startcell;
		
		openList.add(startcell);
		startcell.setG(0);
		startcell.setH(calculateH(startcell, endcell));
		
		Tile startTile = cordsToTile(startcell.getX(), startcell.getY());
		Tile endTile = cordsToTile(endcell.getX(), endcell.getY());
		if(startTile.isWall() || endTile.isWall()) {
			return path;
		}
		if(Game.DEBUG) {
			for (GridCell gridCell : gridCell) {
				gridCell.render = false;
			}	
		}
		while(!openList.isEmpty()){	
			int lowestF = 9999999;//Needs to be faster
			for (GridCell gridCell : openList) {
				if(gridCell.getF() < lowestF){
					current = gridCell;
					lowestF = gridCell.getF();
				}
			}

			if(current == endcell){
				path.add(current);
				while(current != startcell){
					current = current.getPreviousCell();
					path.addFirst(current);
				}
				return path;
			}
			openList.remove(current);
			closedList.add(current);
			LinkedList<GridCell> adjCells = current.getAvailableAdjacentCells();
			for (GridCell neighbor : adjCells) {
				if(Game.DEBUG)neighbor.render = true;
				
				int backG = neighbor.getG();
				GridCell backNeighborPrevCell = neighbor.getPreviousCell();
				if(!closedList.contains(neighbor)){
					neighbor.setPreviousCell(current);
					neighbor.setH(calculateH(neighbor, endcell));
					if(neighbor.isDiagonal(current)){
						neighbor.setG(current.getG()+14);						
					}else{
						neighbor.setG(current.getG()+10);
					}
					if(!openList.contains(neighbor)){
						openList.add(neighbor);
					}else{
						if(neighbor.getG() < backG){
							neighbor.setG(backG);
							neighbor.setPreviousCell(backNeighborPrevCell);
						}
					}
				}
			}
			
			
		}
		return path;
	}

	public LinkedList<GridCell> calculatePathBack(GridCell startcell, GridCell endcell){
		LinkedList<GridCell> path = new LinkedList<GridCell>();
		LinkedList<GridCell> closedList = new LinkedList<GridCell>();
		LinkedList<GridCell> openList = new LinkedList<GridCell>();
		LinkedList<GridCell> allAdjCells = new LinkedList<GridCell>();

		Tile endTile = cordsToTile(endcell.getX(), endcell.getY());
		startcell.G = 0;
		startcell.H = 0;
		
		endcell.G = 0;
		endcell.H = 0;
		
		path.add(startcell);
		
		/*for (GridCell gridCell : gridCell) {//Debug
			gridCell.render = false;
		}*/
		
		boolean error = false;
		for (Block b : endTile.blockList) {
			if(b instanceof IronOre || b instanceof GoldOre || b instanceof Stone || b instanceof StoneWall){
				error = true;
			}
		}
		
		if(error == false){
			while (startcell != endcell){
				closedList.add(startcell);
				openList.remove(startcell);
				allAdjCells.clear();
				for (int i = -1; i < 2; i++) {
					for (int j = -1; j < 2; j++) {
						GridCell adjCell = cordsToGridCells(startcell.getX()-i*10, startcell.getY()-j*10);
						Tile adjTile = cordsToTile(startcell.getX()-i*10, startcell.getY()-j*10);

						/*if(Math.abs(j) == 1 && Math.abs(i) == 1){
							adjCell.G = startcell.G + 14;
						}else{
							adjCell.G = startcell.G + 10;
						}*/
						boolean valid = true;
						for (Block b : adjTile.blockList) {
							if(b instanceof IronOre || b instanceof GoldOre || b instanceof Stone || b instanceof StoneWall){
								valid = false;
							}
						}
						if(!closedList.contains(adjCell) && valid && !openList.contains(adjCell)){
							allAdjCells.add(adjCell);
							adjCell.G = startcell.G + 10;
							adjCell.H = Math.abs(adjCell.getX() - endcell.getX())/10 + Math.abs(adjCell.getY() - endcell.getY())/10;
						}
					}
				}

				if (openList.containsAll(allAdjCells)){
					path.removeLast();
					allAdjCells.clear();
					
					for (int i = -1; i < 2; i++) {
						for (int j = -1; j < 2; j++) {
							GridCell adjCell = cordsToGridCells(path.getLast().getX()-i*10, path.getLast().getY()-j*10);							
							Tile adjTile = cordsToTile(path.getLast().getX()-i*10, path.getLast().getY()-j*10);
							boolean valid = true;
							for (Block b : adjTile.blockList) {
								if(b instanceof IronOre || b instanceof GoldOre || b instanceof Stone){
									valid = false;
								}
							}
							if(!closedList.contains(adjCell) && valid){
								allAdjCells.add(adjCell);
							}
						}
					}
					openList.removeAll(allAdjCells);
					
				}else{
					int lowest = 999999999;
					GridCell bestCell = null;
					for (GridCell gridCell : allAdjCells) {
						if (gridCell.getF() < lowest){
							lowest = (int) gridCell.getF();
							bestCell = gridCell;
							
						}
					}
					path.add(bestCell);
					openList.addAll(allAdjCells);
				}
				/*try {
				TimeUnit.MILLISECONDS.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}*/
				
				startcell = path.getLast();
				
			}			
		}
		return path;
		
	}
}
