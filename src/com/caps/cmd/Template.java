package com.caps.cmd;

import com.caps.main.Game;

public class Template implements IButtonFunctions{
	private Game game;
	public Template(Game g){
		game = g;
	}
	@Override
	public void execute() {
		System.out.println("Kys");
		System.out.println(game.gameState);
	}
	// Kill me now
}
