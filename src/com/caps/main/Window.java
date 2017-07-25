package com.caps.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;



public class Window extends Canvas{

	private static final long serialVersionUID = 1L;
	
	public Window(int width, int height , String title , Game game){
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width , height));
		frame.setMaximumSize(new Dimension(width , height));
		frame.setMinimumSize(new Dimension(width , height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		
		ImageIcon img = null;
		try {
			img = new ImageIcon(ImageIO.read(this.getClass().getResource("/icon.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame.setIconImage(img.getImage());
		
		game.start();
	}
}
