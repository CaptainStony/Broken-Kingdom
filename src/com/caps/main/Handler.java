package com.caps.main;

import java.awt.Graphics;
import java.util.LinkedList;

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
		}else if (object instanceof Stone){
			stone.add((Stone) object);
		}else if (object instanceof IronOre){
			ironOre.add((IronOre) object);
		}else if (object instanceof GoldOre){
			goldOre.add((GoldOre) object);
		}
	}
	
	public void removeObject(Object object){
		if(object instanceof Colonist){
			colonist.remove((Colonist) object);
		}else if (object instanceof Stone){
			stone.remove((Stone) object);
		}else if (object instanceof IronOre){
			ironOre.remove((IronOre) object);
		}else if (object instanceof GoldOre){
			goldOre.remove((GoldOre) object);
		}
	}
	


}