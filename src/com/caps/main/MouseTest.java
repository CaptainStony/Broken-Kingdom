package com.caps.main;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import com.caps.cmd.StartGame;
import com.caps.main.Game.STATE;
import com.caps.objects.Colonist;


public class MouseTest extends MouseAdapter{
	private Game game;
	private Handler handler;
	private Grid grid;
	public MouseTest(Game game,Handler handler,Grid grid){		
		this.game = game;
		this.grid = grid;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		Point mousePoint = new Point(mx, my);
		for (Colonist colonist : handler.Colonist) {
			//gotoCords(cordsToGridCells(mx, my), colonist);
			calculatePath(cordsToGridCells((int)colonist.getX(), (int)colonist.getY()), cordsToGridCells(mx, my), colonist);
		
		}
	}
	
	public LinkedList<GridCell> calculatePath(GridCell startcell, GridCell endcell, Colonist obj){
		LinkedList<GridCell> path = new LinkedList<GridCell>();
		LinkedList<GridCell> allAdjCells = new LinkedList<GridCell>();
		obj.closedList.clear();
		obj.openList.clear();
		startcell.F = 0;
		startcell.G = 0;
		startcell.H = 0;
		
		endcell.F = 0;
		endcell.G = 0;
		endcell.H = 0;
		for (GridCell gridCell : grid.gridCell) {
			gridCell.render = false;
		}
		while (startcell != endcell){
			obj.closedList.add(startcell);
			allAdjCells.clear();
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					GridCell adjCell = cordsToGridCells(startcell.getX()-i*10, startcell.getY()-j*10);
					//adjCell.render = true;
					/*if(Math.abs(j) == 1 && Math.abs(i) == 1){
						adjCell.G = startcell.G + 10;
					}else{
						adjCell.G = startcell.G + 10;
					}*/
					if(!obj.closedList.contains(adjCell)){
						allAdjCells.add(adjCell);
						adjCell.G = startcell.G + 10;
						adjCell.H = Math.abs(adjCell.getX() - endcell.getX())/10 + Math.abs(adjCell.getY() - endcell.getY())/10;
						adjCell.F = adjCell.H + adjCell.G;	
					}

				}
			}
			/*try {
			TimeUnit.MILLISECONDS.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
			int lowest = 999999999;
			GridCell bestCell = null;
			for (GridCell gridCell : allAdjCells) {
				if (gridCell.F < lowest){
					lowest = (int) gridCell.F;
					bestCell = gridCell;

				}
			}
			bestCell.render = true;
			System.out.println(bestCell.F+" "+bestCell.G+" "+bestCell.H);
			path.add(bestCell);
			startcell = path.getLast();
			
		}
		return path;
		
	}
	
	private GridCell cordsToGridCells(int x, int y){
		Point mousePoint = new Point(x, y);
		for (GridCell gridcell : grid.gridCell) {
			if (gridcell.bounds.contains(mousePoint)){
				return gridcell;
			}
		}
		return null;
	}
	
	private void gotoCords(GridCell gridcell,Colonist obj){
		float difX = gridcell.getX() - obj.getX();
		float difY = gridcell.getY() - obj.getY();
		float angl = (float) Math.atan(difY/difX);
		int baseSpeed = 2;
		
		if(difX>0 && difY<0 || difX>0 && difY>0){
			obj.velX = (float) (baseSpeed * Math.cos(angl));
			obj.velY = (float) (baseSpeed * Math.sin(angl));
			
		}else if (difX<0 && difY<0 || difX<0 && difY>0){
			obj.velX = (float) -(baseSpeed * Math.cos(angl));
			obj.velY = (float) -(baseSpeed * Math.sin(angl));
		}
	}
	
	
	
	public void mouseReleased(MouseEvent e){
		
	}
	public void tick(){
		
	}

	public void render(Graphics g){

	}

}
