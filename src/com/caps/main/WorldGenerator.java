package com.caps.main;

import java.awt.Color;

import com.caps.objects.BoundWall;
import com.caps.objects.GoldOre;
import com.caps.objects.Grass;
import com.caps.objects.IronOre;
import com.caps.objects.Stone;

public class WorldGenerator
{
	private int WIDTH;
	private int HEIGHT;
	private static final double FEATURE_SIZE = 24;
	
	public WorldGenerator(long i, Handler handler, Grid grid){
		WIDTH = grid.cellWidth / 2;
		HEIGHT = grid.cellHeight / 2;
		OpenSimplexNoise noise = new OpenSimplexNoise(i);
		for (int j = 0; j < WIDTH; j++) {
			for (int k = 0; k < HEIGHT; k++) {
				if(Game.DEBUG) {
					handler.addBlock(new Grass(j*20, k*20, Color.darkGray));
				}else {
					double value = - noise.eval(j / FEATURE_SIZE, k / FEATURE_SIZE, 0.0);
					if(value < 1 && value > 0.2){
						if(value < 0.4 && value > 0.3){
							handler.addBlock(new IronOre(j * 20, k * 20));
						}else if(value < 0.50 && value > 0.45){
							handler.addBlock(new GoldOre(j * 20, k * 20));
						} else {
							handler.addBlock(new Stone(j * 20, k * 20));
						}
					}else{//grass
						handler.addBlock(new Grass(j * 20, k * 20, new Color(50, (int) (185/(Math.abs(value/1.5)+1)), (int) (100/(Math.abs(value*2)+1)))));
					}	
				}
			}
		}
		//BORDER
		for (int j = 0; j < WIDTH; j++) {
			handler.addBlock(new BoundWall(0, j*20));
			handler.addBlock(new BoundWall((WIDTH-1)*20, j*20));
		}
		for (int k = 0; k < HEIGHT; k++) {
			handler.addBlock(new BoundWall(k*20, 0));
			handler.addBlock(new BoundWall(k*20, (HEIGHT-1)*20));

		}
	}
}
