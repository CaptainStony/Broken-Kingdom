package com.caps.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import com.caps.ButtonHUD.GameButton;
import com.caps.ButtonHUD.SetBuildBlock;
import com.caps.objects.Block;
import com.caps.objects.Colonist;
import com.caps.objects.Flag;
import com.caps.objects.Object;
import com.caps.objects.StoneWall;
import com.caps.objects.WorkCamp;

public class HUD extends KeyAdapter{

	private GameManager gameManager;
	private Handler handler;
	private Grid grid;
	public LinkedList<GameButton> gameButtons = new LinkedList<GameButton>();
	public LinkedList<GameButton> hudButtons = new LinkedList<GameButton>();

	
	public Block selectedBlock;
	public boolean buildMode = false;
	
	public HUD(GameManager gameManager, Handler handler, Grid grid){
		this.gameManager = gameManager;
		this.handler = handler;
		this.grid = grid;
		hudButtons.add(new GameButton(14, 625, 74, 50, "Stone wall", new SetBuildBlock(this, new StoneWall(1,1))));

	}
	
	public void tick(){

	}

	public int ofset = 140;

	public void render(Graphics g){
		
		g.setColor(Color.BLACK);
		g.fillRect(10, 620, 1250, 60);
		g.fillRect(10,10,100,62);
		g.setColor(Color.white);
		g.drawRect(10, 10, 100, 62);
    	g.setFont(new Font("TimesRoman", Font.PLAIN, 16)); 
		g.drawString("Gold: " + gameManager.gold, 12, 26);
		g.drawString("Wood: " + gameManager.wood, 12, 40);
		g.drawString("Stone: " + gameManager.stone, 12, 54);
		g.drawString("Iron: " + gameManager.iron, 12, 68);
		
		if(!gameManager.selectedList.isEmpty()){ //Unit Selected
			//Draw selected unit rect in handler
			g.setColor(Color.white);

			for (int i = 0; i < gameManager.selectedList.size(); i++) {	//Show name and health
		    	g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
				g.drawString(gameManager.selectedList.get(i).getClass().getSimpleName(), 20+(i*ofset), 655);
				g.setColor(Color.green);
				g.fillRect(20+i*ofset, 660, (int) ((Object) gameManager.selectedList.get(i)).getHealth(), 10);
				g.setColor(Color.white);
		    	g.setFont(new Font("TimesRoman", Font.PLAIN, 14)); 
				String s = Integer.toString((int) ((Object) gameManager.selectedList.get(i)).getHealth());
				g.drawString(s, (int) (20+((Object) gameManager.selectedList.get(i)).getHealth()+i*ofset), 670);
			}
			if(gameManager.selectedList.size() == 1){ //Specific unit --> unit actions
				if(gameManager.selectedList.getFirst() instanceof Flag){
					Flag f = (Flag) gameManager.selectedList.getFirst();
					if(!gameButtons.containsAll(f.flagButtons)){
						gameButtons.addAll(f.flagButtons);

					}
				}else if(gameManager.selectedList.getFirst() instanceof Colonist){
					Colonist c = (Colonist) gameManager.selectedList.getFirst();
					if(!gameButtons.containsAll(c.colonistButtons)){
						gameButtons.addAll(c.colonistButtons);
					}
					
				}else if(gameManager.selectedList.getFirst() instanceof WorkCamp){
					WorkCamp w = (WorkCamp) gameManager.selectedList.getFirst();
					g.setColor(Color.white);
			    	g.setFont(new Font("TimesRoman", Font.PLAIN, 14)); 
			    	if(w.mineTimer == -1){
						g.drawString("No Blocks", 250, 655);
			    	}else{
						g.drawString("Mine Timer: "+w.mineTimer, 250, 655);
			    	}
			    	if(!gameButtons.containsAll(w.workCampButtons)){
						gameButtons.addAll(w.workCampButtons);
					}
					
				}
			}
			
			for (GameButton gameButton : gameButtons) { // Draw buttons
				gameButton.render(g);
			}
		}else{ //Normal HUD
			for (GameButton hudButton : hudButtons) { // Draw buttons
				hudButton.render(g);
			}
		}

	}
	
	public boolean[] keyPress = {false,false,false,false};
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_ESCAPE) System.exit(0);
		if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) keyPress[0] = true;
		if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) keyPress[1] = true;
		if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) keyPress[2] = true;
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) keyPress[3] = true;

	}
	
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) keyPress[0] = false;
		if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) keyPress[1] = false;
		if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) keyPress[2] = false;
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) keyPress[3] = false;
		
	}
	
}
