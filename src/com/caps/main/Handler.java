package com.caps.main;

import java.awt.Graphics;
import java.util.LinkedList;

import com.caps.objects.Colonist;
import com.caps.objects.Object;

public class Handler {

	LinkedList<Object> Colonist = new LinkedList<Object>();
	
	
	public void tick(){
		for (Object object : Colonist) {
			object.tick();
		}
	}
	
	public void render(Graphics g){
		for (Object object : Colonist) {
			object.render(g);
		}
	}
	
	public void addObject(Object object){
		if(object instanceof Colonist){
			Colonist.add(object);
		}
	}
	
	public void removeObject(Object object){
		if(object instanceof Colonist){
			Colonist.remove(object);
		}
	}
	


}
