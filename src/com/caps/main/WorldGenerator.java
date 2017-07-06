package com.caps.main;

public class WorldGenerator
{
	private static final int WIDTH = 75;
	private static final int HEIGHT = 75;
	private static final double FEATURE_SIZE = 24;

	public void run(long i){
		OpenSimplexNoise noise = new OpenSimplexNoise(i);
		//double value = noise.eval(x / FEATURE_SIZE, y / FEATURE_SIZE, 0.0);
	}
}
