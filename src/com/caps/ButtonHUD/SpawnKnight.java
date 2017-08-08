package com.caps.ButtonHUD;

import com.caps.ButtonMenu.IButtonFunctions;
import com.caps.main.Grid;
import com.caps.main.Handler;
import com.caps.objects.Flag;
import com.caps.objects.Knight;

public class SpawnKnight implements IButtonFunctions{
	
	private Handler handler;
	private Grid grid;
	private Flag flag;
	
	public SpawnKnight(Handler handler, Grid grid, Flag flag){
		this.handler = handler;
		this.grid = grid;
		this.flag = flag;
	}
	@Override
	public void execute() {
		Knight k = new Knight(flag.getX(), flag.getY(), grid, handler);
		flag.queue.addToQueue(k, 180, flag.getWaypoint());
	}
}
