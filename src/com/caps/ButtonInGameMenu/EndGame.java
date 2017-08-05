package com.caps.ButtonInGameMenu;

import com.caps.ButtonMenu.IButtonFunctions;

public class EndGame implements IButtonFunctions{
	public EndGame(){
		
	}
	@Override
	public void execute() {
		System.exit(1);
	}
}
