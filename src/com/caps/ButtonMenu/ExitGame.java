package com.caps.ButtonMenu;


public class ExitGame implements IButtonFunctions{
	public ExitGame(){
	}
	@Override
	public void execute() {
		System.exit(1);
	}
}
