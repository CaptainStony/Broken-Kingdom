package com.caps.ButtonMenu;

import com.caps.main.Button;
import com.caps.main.Menu;
import com.caps.main.Sound;

public class PlayMusic implements IButtonFunctions{
	private Menu menu;
	public PlayMusic(Menu menu){
		this.menu = menu;
	}
	@Override
	public void execute() {
		Sound.backMusic.toggleMute();
		Sound.click.isMuted = !Sound.click.isMuted;
		Sound.gameStart.isMuted = !Sound.gameStart.isMuted;
		for(Button b : menu.optionButtons){
			if(b.func instanceof PlayMusic){
				if(Sound.backMusic.isMuted){
					b.setText("Sound: muted");
				}else{
					b.setText("Sound: unmuted");
				}
				break;
			}
		}
	}
}
