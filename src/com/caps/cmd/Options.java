package com.caps.cmd;

import com.caps.main.Menu;
import com.caps.main.Menu.STATE;

public class Options implements IButtonFunctions{
	private Menu menu;
	public Options(Menu menu){
		this.menu = menu;
	}
	@Override
	public void execute() {
		menu.menuState = STATE.Options;
	}
}
