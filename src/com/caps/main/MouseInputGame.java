package com.caps.main;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.LinkedList;

import com.caps.ButtonHUD.GameButton;
import com.caps.ButtonInGameMenu.InGameMenuButton;
import com.caps.main.Game.STATE;
import com.caps.objects.Block;
import com.caps.objects.Flag;
import com.caps.objects.GameObject;
import com.caps.objects.StoneWall;


public class MouseInputGame extends MouseAdapter implements MouseWheelListener{
	private GameManager gameManager;
	private Handler handler;
	private Game game;
	private Grid grid;
	private HUD hud;
	private InGameMenu inGameMenu;
	
	public MouseInputGame(Game game,GameManager gameManager, Handler handler ,Grid grid,HUD hud, InGameMenu inGameMenu){		
		this.gameManager = gameManager;
		this.handler = handler;
		this.game = game;
		this.grid = grid;
		this.hud = hud;
		this.inGameMenu = inGameMenu;
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
						GameObject o = null;
						o = gameManager.selectedList.get((mx-10)/hud.ofset);
						gameManager.selectedList.clear();
						gameManager.selectedList.add(o);
					}
				}
				
			}else{ //WORLD
				if(e.getButton() == 1){//left click
					if(hud.selectedBlock != null){//Place block
						if(checksufficientResources(hud.selectedBlock)){
							handler.addBlock(hud.selectedBlock);
							removeResoursesToBuildBlock(hud.selectedBlock);
							hud.showRegularMessage(hud.selectedBlock.getClass().getSimpleName() + " build!");	
						}else{
							hud.showRegularMessage("Insufficient resources!");	
						}
					}else{//Get clicked obj
						gameManager.selectedList.clear();
						hud.gameButtons.clear();
						gameManager.selectedList = getHitObject(worldX, worldY);	
					}
					//System.out.println(gameManager.selectedList);
				}else if(e.getButton() == 2){
					
				}else if(e.getButton() == 3){//right click
					if(gameManager.selectedList.size() == 1 && gameManager.selectedList.getFirst() instanceof Flag) {
						Flag f = (Flag) gameManager.selectedList.getFirst();
						f.setWaypoint(grid.cordsToGridCells(worldX, worldY));
					}
					hud.selectedBlock = null;
					hud.buildMode = false;
					for (GameObject o : gameManager.selectedList) {//Pathfinding
						GridCell startCell = grid.cordsToGridCells((int)(o.getX()), (int)(o.getY()));
						GridCell endCell = grid.cordsToGridCells(worldX, worldY);
						o.setVelX(0);
						o.setVelY(0);
						o.setPath(grid.calculatePath(startCell, endCell));
					}
				}
			}
		}else if(Game.gameState == STATE.InGameMenu){
			for (InGameMenuButton menuB : inGameMenu.startMenuButtons) {//Button click
				if(menuB.getBounds().contains(mx, my)){
					menuB.click();
					break;
				}
			}
		}
	}
	private void removeResoursesToBuildBlock(Block b){
		gameManager.IRON -= b.getIronNeededToBuild();
		gameManager.STONE -= b.getStoneNeededToBuild();
		gameManager.GOLD -= b.getGoldNeededToBuild();
	}
	
	private boolean checksufficientResources(Block b){
		if(b.getGoldNeededToBuild() <= gameManager.GOLD && b.getStoneNeededToBuild() <= gameManager.STONE && b.getIronNeededToBuild() <= gameManager.IRON){
			return true;
		}
		return false;
	}
	
	
	public void mouseMoved(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		int worldX = (int) ((mx/gameManager.scale - gameManager.camX));
		int worldY = (int) ((my/gameManager.scale - gameManager.camY));
		if(hud.buildMode){ // Ghost block
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
	
	private LinkedList<GameObject> getHitObject(int x,int y){
		LinkedList<GameObject> ret = new LinkedList<GameObject>();
		GridCell c = grid.cordsToGridCells(x, y);
		Point p = new Point(x,y);
		
		for (GameObject object : c.objectList) {
			if(object.getBoundsTotal().contains(p)) {
				ret.add(object);
			}
		}
		
		return ret;
	}
}
