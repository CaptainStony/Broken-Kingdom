package com.caps.ButtonMenu;

import com.caps.main.Menu;
import com.caps.main.Menu.STATE;

public class Options implements IButtonFunctions{
	public Options(){
	}
	@Override
	public void execute() {
		Menu.menuState = STATE.Options;
	}
}
