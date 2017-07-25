package com.caps.ButtonHUD;

import com.caps.cmd.IButtonFunctions;
import com.caps.main.Grid;
import com.caps.main.GridCell;
import com.caps.main.Handler;
import com.caps.objects.Knight;

public class SpawnKnight implements IButtonFunctions{
	
	private Handler handler;
	private Grid grid;
	private GridCell startCell;
	
	public SpawnKnight(Handler handler, Grid grid, GridCell startCell){
		this.handler = handler;
		this.grid = grid;
		this.startCell = startCell;
	}
	@Override
	public void execute() {
		Knight k = new Knight(startCell.getX(), startCell.getY(), grid);
		k.setPath(grid.calculatePath(startCell, handler.flag.waypoint));
		handler.addObject(k);
	}
}
