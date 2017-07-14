package com.caps.main;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import com.caps.cmd.StartGame;
import com.caps.main.Game.STATE;
import com.caps.objects.Colonist;


public class MouseInputGame extends MouseAdapter implements MouseWheelListener{
	private GameManager gameManager;
	private Handler handler;
	private Game game;
	public MouseInputGame(Game game,GameManager gameManager, Handler handler){		
		this.gameManager = gameManager;
		this.handler = handler;
		this.game = game;
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		Point mousePoint = new Point(mx, my);
		if (game.gameState == STATE.Game){
			
		}
	}
	
	public void mouseMoved(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		Point mousePoint = new Point(mx, my);
	}
	
	public void mouseWheelMoved(MouseWheelEvent e){
		int dir = e.getWheelRotation();
		//is wa skeer
		if(gameManager.scale <= 1.6){
			gameManager.scale -= (double)dir/20;			
		}else{
			gameManager.scale = 1.6;
		}
		if(gameManager.scale >= 0.25){
			gameManager.scale -= (double)dir/20;			
		}else{
			gameManager.scale = 0.25;
		}


	}
	
	public void mouseReleased(MouseEvent e){
		
	}
	public void tick(){
		
	}

	public void render(Graphics g){

	}

}
