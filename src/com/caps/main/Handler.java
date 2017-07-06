package com.caps.main;

import java.awt.Graphics;
import java.util.LinkedList;

import com.caps.objects.Colonist;
import com.caps.objects.GoldOre;
import com.caps.objects.IronOre;
import com.caps.objects.Object;
import com.caps.objects.Stone;

public class Handler {

	LinkedList<Colonist> Colonist = new LinkedList<Colonist>();
	LinkedList<Stone> stones = new LinkedList<Stone>();
	LinkedList<IronOre> ironOre = new LinkedList<IronOre>();
	LinkedList<GoldOre> goldOre = new LinkedList<GoldOre>();

	public void tick(){
		 for (Object object : Colonist) {
			 object.tick();
		 }
		 for(Stone s : stones){
			 s.tick();
		 }
	}	
		 
	public void render(Graphics g){
		 for (Object object : Colonist) {
			  object.render(g);
		 }
		 for(Stone s : stones){
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
			Colonist.add((Colonist) object);
		}
	}
	
	public void removeObject(Object object){
		if(object instanceof Colonist){
			Colonist.remove(object);
		}
	}
	


}