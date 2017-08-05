package com.caps.objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.LinkedList;

import com.caps.ButtonHUD.GameButton;
import com.caps.ButtonHUD.PackWorkCamp;
import com.caps.main.GameManager;
import com.caps.main.Grid;
import com.caps.main.GridCell;
import com.caps.main.Handler;
import com.caps.main.Tile;

public class WorkCamp extends GameObject{
	
	private Rectangle bounds;
	public Ellipse2D workArea;
	private Grid grid;
	private GameManager gameManager;
	private Handler handler;
	
	public LinkedList<GameButton> workCampButtons = new LinkedList<GameButton>();
	
	public int mineTimer = 200;
	
	public WorkCamp(float x, float y,Grid grid, Handler handler, GameManager gameManager){
		super(x,y);
		bounds = new Rectangle((int) x, (int) y, 20, 20);
		this.grid = grid;
		this.handler = handler;
		this.gameManager = gameManager;
		gridUpdate();
		health = 50;
		workArea = new Ellipse2D.Double(x-120, y-120, 250, 250);
		workCampButtons.add(new GameButton(120, 625, 120, 50, "Pack Work Camp", new PackWorkCamp(handler, grid, gameManager, this)));
		blockToMine = getBlockToMine();

	}

	private Block getBlockToMine(){
		for (int i = (int) -workArea.getMinX(); i < workArea.getMaxX(); i+=20) {
			for (int j = (int) -workArea.getMinY(); j < workArea.getMaxY(); j+=20) {
				Tile t = grid.cordsToTile((int)x+i, (int)y+j);
				if(!t.blockList.isEmpty()){
					if(workArea.intersects(t.getBounds()) && !(t.blockList.getFirst() instanceof Grass)){
						return t.blockList.getFirst();
					}
				}
			}
		}
		return null;
	}
	
	private void mineBlock(Block b){
		handler.removeBlock(b);
		if(b instanceof IronOre){
			gameManager.IRON += 50;
		}else if(b instanceof Stone){
			gameManager.STONE += 50;
		}else if(b instanceof GoldOre){
			gameManager.GOLD += 50;
		}
	}
	
	private Block blockToMine = null;
	@Override
	public void tick() {
		if(blockToMine == null){// No block to mine
			mineTimer = -1;
		}else{// Blocks to mine!
			if(mineTimer <= 0){
				mineBlock(blockToMine);
				blockToMine = getBlockToMine();
				mineTimer = 100;
			}else{
				mineTimer--;
			}
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect((int) x, (int) y, 20, 20);
		g.setColor(Color.black);
    	g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
		g.drawString("W", (int)x+2, (int)y+16);
	}

	@Override
	public Rectangle getBoundsTotal() {
		return bounds;
	}
	
	LinkedList<GridCell> prevC = new LinkedList<GridCell>();
	private void gridUpdate(){
		for (GridCell pc : prevC) {
			pc.objectList.remove(this);
		}
		prevC.clear();
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				GridCell c = grid.cordsToGridCells((int)x+i, (int)y+j);
				if(!c.objectList.contains(this)){
					c.objectList.add(this);
					prevC.add(c);
				}
			}
		}
	}
}
