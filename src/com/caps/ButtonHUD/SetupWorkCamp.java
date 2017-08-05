package com.caps.ButtonHUD;

import com.caps.ButtonMenu.IButtonFunctions;
import com.caps.main.GameManager;
import com.caps.main.Grid;
import com.caps.main.Handler;
import com.caps.objects.Colonist;
import com.caps.objects.WorkCamp;

public class SetupWorkCamp implements IButtonFunctions{
	
	private Handler handler;
	private Grid grid;
	private Colonist colonist;
	private GameManager gameManager;
	
	public SetupWorkCamp(Handler handler,Grid grid,GameManager gameManager,Colonist colonist){
		this.handler = handler;
		this.grid = grid;
		this.colonist = colonist;
		this.gameManager = gameManager;
	}
	@Override
	public void execute() {
		handler.addObject(new WorkCamp(colonist.getX(), colonist.getY(), grid,handler, gameManager));
		gameManager.selectedList.clear();
		handler.removeObject(colonist);
		
	}
}
