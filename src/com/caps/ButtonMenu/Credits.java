package com.caps.ButtonMenu;

import com.caps.main.Game;
import com.caps.main.Menu;
import com.caps.main.Menu.STATE;

public class Credits implements IButtonFunctions{
	//private Game game;
	public Credits(Game game){
		//this.game = game;
	}
	@Override
	public void execute() {
		Menu.menuState = STATE.Credits;
	}
}
