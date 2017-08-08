package com.caps.main;

import java.util.LinkedList;

import com.caps.objects.GameObject;

public class Queue {
	
	private LinkedList<GameObject> entitieQueue = new LinkedList<GameObject>();
	private LinkedList<Integer> entitieTickQueue = new LinkedList<Integer>();
	private LinkedList<GridCell> entitieEndCell = new LinkedList<GridCell>();
	private Handler handler;
	private Grid grid;
	
	public Queue(Handler handler, Grid grid){
		this.handler = handler;
		this.grid = grid;
	}
	
	public void tick(){
		if(entitieQueue.size() > 0){
			entitieTickQueue.set(0, entitieTickQueue.get(0) - 1);
			if(entitieTickQueue.get(0) == 0){
				GameObject g = entitieQueue.get(0);
				g.setPath(grid.calculatePath(grid.cordsToGridCells((int) g.getX(), (int) g.getY()), entitieEndCell.get(0)));
				handler.addObject(entitieQueue.get(0));
				removeFirst();
			}
		}
		
	}
	
	public void addToQueue(GameObject object, int ticks, GridCell endCell){
		entitieQueue.add(object);
		entitieTickQueue.add(ticks);
		entitieEndCell.add(endCell);
	}
	private void removeFirst(){
		entitieQueue.remove(0);
		entitieTickQueue.remove(0);
		entitieEndCell.remove(0);
	}
}
