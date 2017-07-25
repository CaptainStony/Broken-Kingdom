package com.caps.ButtonHUD;

import com.caps.cmd.IButtonFunctions;
import com.caps.main.Handler;
import com.caps.objects.Block;

public class BuildBlock implements IButtonFunctions{
	
	private Handler handler;

	private Block block;
	
	public BuildBlock(Handler handler, Block block){
		this.handler = handler;
		this.block = block;
	}
	@Override
	public void execute() {
		handler.addBlock(block);
	}
}
