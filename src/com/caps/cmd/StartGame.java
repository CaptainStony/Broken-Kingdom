package com.caps.cmd;

import com.caps.main.Game;
import com.caps.main.Game.STATE;

public class StartGame implements IButtonFunctions{
	private Game game;
	public StartGame(Game game){
		this.game = game;
	}
	@SuppressWarnings("static-access")
	@Override
	public void execute() {
		game.gameState = STATE.Game;
	}
}
