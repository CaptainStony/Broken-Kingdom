package com.caps.cmd;

import com.caps.main.Game;

public class Template implements IButtonFunctions{
	private Game game;
	public Template(Game g){
		game = g;
	}
	@SuppressWarnings("static-access")
	@Override
	public void execute() {
		System.out.println(game.gameState);
	}
}
