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
		gameManager.scaleX -= (double)dir/20;
		gameManager.scaleY -= (double)dir/20;	
		
		//gameManager.camX += e.getX()/50;
		//gameManager.camY -= e.getY()/50;

	}
	
	public void mouseReleased(MouseEvent e){
		
	}
	public void tick(){
		
	}

	public void render(Graphics g){

	}

}
