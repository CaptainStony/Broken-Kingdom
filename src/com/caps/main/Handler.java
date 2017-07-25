package com.caps.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

import com.caps.objects.Block;
import com.caps.objects.Colonist;
import com.caps.objects.Flag;
import com.caps.objects.GoldOre;
import com.caps.objects.IronOre;
import com.caps.objects.Knight;
import com.caps.objects.Object;
import com.caps.objects.Stone;
import com.caps.objects.StoneWall;
import com.caps.objects.WorkCamp;

public class Handler {

	LinkedList<Colonist> enemy = new LinkedList<Colonist>();
	
	LinkedList<Colonist> colonist = new LinkedList<Colonist>();
	LinkedList<Knight> knight = new LinkedList<Knight>();

	LinkedList<Stone> stone = new LinkedList<Stone>();
	LinkedList<IronOre> ironOre = new LinkedList<IronOre>();
	LinkedList<GoldOre> goldOre = new LinkedList<GoldOre>();
	
	LinkedList<StoneWall> stoneWall = new LinkedList<StoneWall>();
	
	LinkedList<WorkCamp> workCamp = new LinkedList<WorkCamp>();

	
	public Flag flag = null;
	
	private Grid grid;
	private GameManager gameManager;
	
	public Handler(Grid grid, GameManager gameManager) {
		this.grid = grid;
		this.gameManager = gameManager;
	}

	public void tick(){
		if(!gameManager.preGameActive){
			 for (Object object : colonist) {
				 object.tick();
			 }
			 for (Object object : knight) {
				 object.tick();
			 }
			 for (Object object : enemy) {
				 object.tick();
			 }
			 for (Object object : workCamp) {
				 object.tick();
			 }
			/* for(Stone s : stone){
				 s.tick();
			 }*/
			 if(flag != null){
				 flag.tick();
			 }	
		}
	}	
		 
	public void render(Graphics g){
		if(!gameManager.preGameActive){
			 for (Object object : colonist) {
				  object.render(g);
			 }
			 for (Object object : knight) {
				 object.render(g);
			 }
			 for (Object object : enemy) {
				 object.render(g);
			 }
			 for (Object object : workCamp) {
				 object.render(g);
			 }
			/* for(Stone s : stone){
				  s.render(g);
			 }
			 for(IronOre s : ironOre){ // Is naar gridcell verplaatst
				  s.render(g);
			 }
			 for(GoldOre s : goldOre){
				  s.render(g);
			 }*/

			 if(flag != null){
				 flag.render(g);			 
			 }
			for (int i = 0; i < gameManager.selectedList.size(); i++) { //Draw rect to selected
				Object o = (Object) gameManager.selectedList.get(i);
				g.drawRect((int)o.getX(), (int)o.getY(), (int)o.getBoundsTotal().getWidth(), (int)o.getBoundsTotal().getHeight());
				if(o instanceof WorkCamp){//Draw work cirkle
					Graphics2D g2 = (Graphics2D) g;
					g.setColor(Color.white);
					g2.draw(((WorkCamp)o).workArea);
				}
			}
			
		}
	}
	

	
	public void addObject(Object object){
		if(object instanceof Colonist){
			colonist.add((Colonist) object);
		}else if(object instanceof Flag){
			flag = (Flag) object;
		}else if(object instanceof Knight){
			knight.add((Knight) object);
		}else if(object instanceof WorkCamp){
			workCamp.add((WorkCamp) object);
		}
	}
	
	public void removeObject(Object object){
		if(object instanceof Colonist){
			colonist.remove((Colonist) object);
		}else if(object instanceof Knight){
			knight.remove((Knight) object);
		}else if(object instanceof WorkCamp){
			workCamp.remove((WorkCamp) object);
		}
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				GridCell c = grid.cordsToGridCells((int)object.getX()+i, (int)object.getY()+j);
				c.objectList.remove(object);
			}
		}
	}
	
	public void addBlock(Block block){
		if (block instanceof Stone){
			stone.add((Stone) block);
		}else if (block instanceof IronOre){
			ironOre.add((IronOre) block);
		}else if (block instanceof GoldOre){
			goldOre.add((GoldOre) block);
		}else if(block instanceof StoneWall){
			stoneWall.add((StoneWall) block);
		}
		cordsToTile((int)block.getX(), (int)block.getY()).blockList.add(block);
	}
	
	public void removeBlock(Block block){
		if (block instanceof Stone){
			stone.remove((Stone) block);
		}else if (block instanceof IronOre){
			ironOre.remove((IronOre) block);
		}else if (block instanceof GoldOre){
			goldOre.remove((GoldOre) block);
		}
		cordsToTile((int)block.getX(), (int)block.getY()).blockList.remove(block);
	}
	
	public GridCell cordsToGridCells(int x, int y){
		
		return grid.world[(int)x/10][(int)y/10];
	}
	public Tile cordsToTile(int x, int y){
		
		return grid.worldTile[(int)x/20][(int)y/20];
	}

}