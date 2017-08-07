package com.caps.main;

import com.caps.objects.Zombie;

public class Event{
	
	private int attackScore = 0;
	
	public enum EVENT{
		ZombieAttack,
		
	};
	
	public Event(GameManager gameManager, Handler handler ,Grid grid, HUD hud,EVENT event){
		
		//Calculate attack force
		attackScore += handler.colonist.size()/2;
		attackScore += handler.workCamp.size()/2;
		attackScore += handler.knight.size()*1.5;
		attackScore *= 1.2;
		switch (event) {
		case ZombieAttack:
			hud.showAlertMessage("Zombie attack: " + attackScore);
			int enemysPlaced = 0;
			for (int i = 50; i < grid.cellWidth/10; i++) {
				enemysPlaced++;
				handler.addObject(new Zombie(i*21, 70, grid, gameManager, handler));
				if(enemysPlaced == attackScore) break;
			}

			break;

		default:
			break;
		}
		
	}
	
	public void tick(){
		
		
	}
}
