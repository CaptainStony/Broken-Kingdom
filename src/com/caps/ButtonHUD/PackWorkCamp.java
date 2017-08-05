package com.caps.ButtonHUD;

import com.caps.ButtonMenu.IButtonFunctions;
import com.caps.main.GameManager;
import com.caps.main.Grid;
import com.caps.main.Handler;
import com.caps.objects.Colonist;
import com.caps.objects.WorkCamp;

public class PackWorkCamp implements IButtonFunctions{
	
	private Handler handler;
	private Grid grid;
	private WorkCamp workCamp;
	private GameManager gameManager;
	
	public PackWorkCamp(Handler handler,Grid grid,GameManager gameManager,WorkCamp workCamp){
		this.handler = handler;
		this.grid = grid;
		this.workCamp = workCamp;
		this.gameManager = gameManager;
	}
	@Override
	public void execute() {
		handler.addObject(new Colonist(workCamp.getX(), workCamp.getY(), grid,handler, gameManager));
		gameManager.selectedList.clear();
		handler.removeObject(workCamp);
		
	}
}
