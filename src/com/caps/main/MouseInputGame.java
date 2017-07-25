package com.caps.main;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.LinkedList;

import com.caps.ButtonHUD.GameButton;
import com.caps.main.Game.STATE;
import com.caps.objects.Block;
import com.caps.objects.Colonist;
import com.caps.objects.Flag;
import com.caps.objects.Knight;
import com.caps.objects.StoneWall;
import com.caps.objects.WorkCamp;


public class MouseInputGame extends MouseAdapter implements MouseWheelListener{
	private GameManager gameManager;
	private Handler handler;
	private Game game;
	private Grid grid;
	private HUD hud;
	
	public MouseInputGame(Game game,GameManager gameManager, Handler handler ,Grid grid,HUD hud){		
		this.gameManager = gameManager;
		this.handler = handler;
		this.game = game;
		this.grid = grid;
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		int worldX = (int) ((mx/gameManager.scale - gameManager.camX));
		int worldY = (int) ((my/gameManager.scale - gameManager.camY));
		if (Game.gameState == STATE.Game){
			
			if(my>620){ //HUD
				if(gameManager.selectedList.isEmpty()){
					for (GameButton hudB : hud.hudButtons) {//Button click in hud
						if(hudB.getBounds().contains(mx, my)){
							hudB.click();
							break;
						}
					}
				}else{
					for (GameButton gameB : hud.gameButtons) {//Button click in unit
						if(gameB.getBounds().contains(mx, my)){
							gameB.click();
							break;
						}
					}
				}
				if(e.getButton() == 1){
					if(gameManager.selectedList.size() > 1){
						Object o = null;
						o = gameManager.selectedList.get((mx-10)/hud.ofset);
						gameManager.selectedList.clear();
						gameManager.selectedList.add(o);
					}
				}
				
			}else{ //WORLD
				if(e.getButton() == 1){
					if(hud.selectedBlock != null){//Place block
						handler.addBlock(hud.selectedBlock);
					}else{//Get hit obj
						gameManager.selectedList.clear();
						hud.gameButtons.clear();
						gameManager.selectedList = getHitObject(worldX, worldY);	
					}
					//System.out.println(gameManager.selectedList);
				}else if(e.getButton() == 2){
					
				}else if(e.getButton() == 3){
					hud.selectedBlock = null;
					hud.buildMode = false;
					for (Object o : gameManager.selectedList) {//Pathfinding and flag waypointing
						if(o instanceof Colonist){
							GridCell startCell = grid.cordsToGridCells((int)((Colonist) o).getX(), (int)((Colonist) o).getY());
							GridCell endCell = grid.cordsToGridCells(worldX, worldY);
							((Colonist) o).setPath(grid.calculatePath(startCell, endCell));
						}else if(o instanceof Knight){
							GridCell startCell = grid.cordsToGridCells((int)((Knight) o).getX(), (int)((Knight) o).getY());
							GridCell endCell = grid.cordsToGridCells(worldX, worldY);
							((Knight) o).setPath(grid.calculatePath(startCell, endCell));
						}else if(o instanceof Flag){
							GridCell c = grid.cordsToGridCells(worldX, worldY);
							((Flag)o).waypoint = c;
							
						}
					}
				}
			}	
		}
	}
	
	public void mouseMoved(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		int worldX = (int) ((mx/gameManager.scale - gameManager.camX));
		int worldY = (int) ((my/gameManager.scale - gameManager.camY));
		if(hud.buildMode){
			Tile t = grid.cordsToTile(worldX, worldY);
			boolean exists = false;
			for (Block b : t.blockList) {
				if(b.getClass() == hud.selectedBlock.getClass()){
					exists = true;
					break;
				}
			}
			if(!exists){
				if(hud.selectedBlock instanceof StoneWall){
					hud.selectedBlock = new StoneWall(t.getX(), t.getY());				
				}	
			}
		}
		
		
	}
	
	public void mouseReleased(MouseEvent e){
		
	}
	
	public float dir;
	public void mouseWheelMoved(MouseWheelEvent e){
		dir = e.getWheelRotation();
		gameManager.goScale = true;

	}
	
	private LinkedList<Object> getHitObject(int x,int y){
		LinkedList<Object> ret = new LinkedList<Object>();
		GridCell c = grid.cordsToGridCells(x, y);
		Point p = new Point(x,y);
		
		for (Object object : c.objectList) {
			if(object instanceof Colonist){
				if(((Colonist) object).getBoundsTotal().contains(p)){
					ret.add(object);
				}
			}else if(object instanceof Flag){
				if(((Flag) object).getBoundsTotal().contains(p)){
					ret.add(object);
				}
			}else if(object instanceof Knight){
				if(((Knight) object).getBoundsTotal().contains(p)){
					ret.add(object);
				}
			}else if(object instanceof WorkCamp){
				if(((WorkCamp) object).getBoundsTotal().contains(p)){
					ret.add(object);
				}
			}
		}
		
		return ret;
	}
}
