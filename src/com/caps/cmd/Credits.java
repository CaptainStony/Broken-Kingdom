package com.caps.cmd;

import com.caps.main.Game;
import com.caps.main.Menu;
import com.caps.main.Menu.STATE;

public class Credits implements IButtonFunctions{
	private Game game;
	private Menu menu;
	public Credits(Game game, Menu menu){
		this.game = game;
		this.menu = menu;
	}
	@Override
	public void execute() {
		menu.menuState = STATE.Credits;
	}
}
