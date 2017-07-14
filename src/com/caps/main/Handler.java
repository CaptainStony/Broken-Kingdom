package com.caps.main;

import java.awt.Graphics;
import java.util.LinkedList;

import com.caps.objects.Block;
import com.caps.objects.Colonist;
import com.caps.objects.GoldOre;
import com.caps.objects.IronOre;
import com.caps.objects.Object;
import com.caps.objects.Stone;

public class Handler {

	LinkedList<Colonist> colonist = new LinkedList<Colonist>();
	LinkedList<Stone> stone = new LinkedList<Stone>();
	LinkedList<IronOre> ironOre = new LinkedList<IronOre>();
	LinkedList<GoldOre> goldOre = new LinkedList<GoldOre>();

	public void tick(){
		 for (Object object : colonist) {
			 object.tick();
		 }
		 for(Stone s : stone){
			 s.tick();
		 }
	}	
		 
	public void render(Graphics g){
		 for (Object object : colonist) {
			  object.render(g);
		 }
		 for(Stone s : stone){
			  s.render(g);
		 }
		 for(IronOre s : ironOre){
			  s.render(g);
		 }
		 for(GoldOre s : goldOre){
			  s.render(g);
		 }
	}
	

	
	public void addObject(Object object){
		if(object instanceof Colonist){
			colonist.add((Colonist) object);
		}
	}
	public void addBlock(Block block){
		if (block instanceof Stone){
			stone.add((Stone) block);
		}else if (block instanceof IronOre){
			ironOre.add((IronOre) block);
		}else if (block instanceof GoldOre){
			goldOre.add((GoldOre) block);
		}
	}
	
	public void removeObject(Object object){
		if(object instanceof Colonist){
			colonist.remove((Colonist) object);
		}
	}
	public void removeBlock(Block block){
		if (block instanceof Stone){
			stone.remove((Stone) block);
		}else if (block instanceof IronOre){
			ironOre.remove((IronOre) block);
		}else if (block instanceof GoldOre){
			goldOre.remove((GoldOre) block);
		}
	}
	


}