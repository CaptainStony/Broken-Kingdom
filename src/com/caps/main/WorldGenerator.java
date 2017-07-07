package com.caps.main;

import com.caps.objects.GoldOre;
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
				double value = - noise.eval(j / FEATURE_SIZE, k / FEATURE_SIZE, 0.0);
				if(value < 0.4 && value > 0.0){
					if(value < 0.2 && value > 0.1){
						handler.addObject(new IronOre(j * 20, k * 20));
					}else if(value < 0.4 && value > 0.38){
						handler.addObject(new GoldOre(j * 20, k * 20));
					} else {
						handler.addObject(new Stone(j * 20, k * 20));
					}
				}
			}
		}
		//double value = noise.eval(x / FEATURE_SIZE, y / FEATURE_SIZE, 0.0);
	}
}
