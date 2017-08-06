package com.caps.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

import com.caps.objects.Block;
import com.caps.objects.BoundWall;
import com.caps.objects.Colonist;
import com.caps.objects.Flag;
import com.caps.objects.GameObject;
import com.caps.objects.GoldOre;
import com.caps.objects.IronOre;
import com.caps.objects.Knight;
import com.caps.objects.Stone;
import com.caps.objects.StoneWall;
import com.caps.objects.WorkCamp;
import com.caps.objects.Zombie;

public class Handler {

	LinkedList<GameObject> enemy = new LinkedList<GameObject>();
	
	LinkedList<Colonist> colonist = new LinkedList<Colonist>();
	LinkedList<Knight> knight = new LinkedList<Knight>();

	LinkedList<Stone> stone = new LinkedList<Stone>();
	LinkedList<IronOre> ironOre = new LinkedList<IronOre>();
	LinkedList<GoldOre> goldOre = new LinkedList<GoldOre>();
	
	LinkedList<StoneWall> stoneWall = new LinkedList<StoneWall>();
	LinkedList<BoundWall> boundWall = new LinkedList<BoundWall>();

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
			 for (GameObject GameObject : colonist) {
				 GameObject.tick();
			 }
			 for (GameObject GameObject : knight) {
				 GameObject.tick();
			 }
			 for (GameObject GameObject : enemy) {
				 GameObject.tick();
			 }
			 for (GameObject GameObject : workCamp) {
				 GameObject.tick();
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
			 for (GameObject GameObject : colonist) {
				  GameObject.render(g);
			 }
			 for (GameObject GameObject : knight) {
				 GameObject.render(g);
			 }
			 for (GameObject GameObject : enemy) {
				 GameObject.render(g);
			 }
			 for (GameObject GameObject : workCamp) {
				 GameObject.render(g);
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
				GameObject o = (GameObject) gameManager.selectedList.get(i);
				g.drawRect((int)o.getX(), (int)o.getY(), (int)o.getBoundsTotal().getWidth(), (int)o.getBoundsTotal().getHeight());
				if(o instanceof WorkCamp){//Draw work cirkle
					Graphics2D g2 = (Graphics2D) g;
					g.setColor(Color.white);
					g2.draw(((WorkCamp)o).workArea);
				}
			}
			
		}
	}
	

	
	public void addObject(GameObject GameObject){
		if(GameObject instanceof Colonist){
			colonist.add((Colonist) GameObject);
		}else if(GameObject instanceof Flag){
			flag = (Flag) GameObject;
		}else if(GameObject instanceof Knight){
			knight.add((Knight) GameObject);
		}else if(GameObject instanceof WorkCamp){
			workCamp.add((WorkCamp) GameObject);
		}else if(GameObject instanceof Zombie) {
			enemy.add(GameObject);
		}
	}
	
	public void removeObject(GameObject GameObject){
		if(GameObject instanceof Colonist){
			colonist.remove((Colonist) GameObject);
		}else if(GameObject instanceof Knight){
			knight.remove((Knight) GameObject);
		}else if(GameObject instanceof WorkCamp){
			workCamp.remove((WorkCamp) GameObject);
		}else if(GameObject instanceof Zombie) {
			enemy.remove(GameObject);
		}
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				GridCell c = grid.cordsToGridCells((int)GameObject.getX()+i, (int)GameObject.getY()+j);
				c.objectList.remove(GameObject);
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
		}else if(block instanceof BoundWall) {
			boundWall.add((BoundWall) block);
		}
		grid.cordsToTile((int)block.getX(), (int)block.getY()).blockList.add(block);
	}
	
	public void removeBlock(Block block){
		if (block instanceof Stone){
			stone.remove((Stone) block);
		}else if (block instanceof IronOre){
			ironOre.remove((IronOre) block);
		}else if (block instanceof GoldOre){
			goldOre.remove((GoldOre) block);
		}else if(block instanceof BoundWall) {
			boundWall.remove((BoundWall) block);
		}
		grid.cordsToTile((int)block.getX(), (int)block.getY()).blockList.remove(block);
	}

}