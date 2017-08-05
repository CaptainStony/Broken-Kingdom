package com.caps.ButtonHUD;

import com.caps.ButtonMenu.IButtonFunctions;
import com.caps.main.HUD;
import com.caps.objects.Block;

public class SetBuildBlock implements IButtonFunctions{
	
	private HUD hud;
	private Block block;
	
	public SetBuildBlock(HUD hud, Block block){
		this.hud = hud;
		this.block = block;
	}
	
	@Override
	public void execute() {
		hud.buildMode = true;
		hud.selectedBlock = block;
	}
}
