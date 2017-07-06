package com.caps.cmd;

import com.caps.main.Game;
import com.caps.main.Game.STATE;
import com.caps.main.GameManager;
import com.caps.main.KeyInput;

public class StartGame implements IButtonFunctions{
	private Game game;
	
	public StartGame(Game game){
		this.game = game;
	}
	@SuppressWarnings("static-access")
	@Override
	public void execute() {
		game.gamemanager = new GameManager(game);
		game.gameState = STATE.Game;

	}
}
