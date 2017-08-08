package com.caps.main;

import java.util.LinkedList;

import com.caps.objects.GameObject;

public class Queue {
	
	public LinkedList<GameObject> entitieQueue = new LinkedList<GameObject>();
	public LinkedList<Integer> entitieTickQueue = new LinkedList<Integer>();
	private Handler handler;
	private Grid grid;
	
	public Queue(Handler handler, Grid grid){
		this.handler = handler;
		this.grid = grid;
	}
	
	public void tick(){
		if(entitieQueue.size() > 0){
			entitieTickQueue.set(0, entitieTickQueue.getFirst() - 1);
			if(entitieTickQueue.get(0) == 0){
				GameObject g = entitieQueue.getFirst();
				handler.addObject(entitieQueue.getFirst());
				g.velX = 1;
				g.velY = 1;
				g.setPath(grid.calculatePath(grid.cordsToGridCells((int) g.getX(), (int) g.getY()), handler.flag.waypoint));
				removeFirst();
			}
		}
		
	}
	
	public void addToQueue(GameObject object, int ticks, GridCell endCell){
		entitieQueue.add(object);
		entitieTickQueue.add(ticks);
	}
	private void removeFirst(){
		entitieQueue.removeFirst();
		entitieTickQueue.removeFirst();
	}
	public void removeIndex(int i){
		entitieQueue.remove(i);
		entitieTickQueue.remove(i);
	}
}
