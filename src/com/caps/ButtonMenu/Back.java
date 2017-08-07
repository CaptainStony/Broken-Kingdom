package com.caps.ButtonMenu;

import com.caps.main.Menu;

public class Back implements IButtonFunctions{
	public Back(){
	}
	@Override
	public void execute() {
		Menu.menuState = Menu.STATE.None;
	}
}
