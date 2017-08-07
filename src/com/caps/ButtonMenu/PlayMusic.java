package com.caps.ButtonMenu;

import com.caps.main.Sound;

public class PlayMusic implements IButtonFunctions{
	public PlayMusic(){
		
	}
	@Override
	public void execute() {
		Sound.backMusic.toggleMute();
	}
}
