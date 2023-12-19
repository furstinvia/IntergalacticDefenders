package com.game.src.opt;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.game.src.loader.BufferedImageLoader;

public class Difficulty {

	BufferedImage menuImg;
	String menuPath = "/easymediumhard.png";

	public Rectangle easyButton = new Rectangle(210, 170, 80, 60);
	public Rectangle mediumButton = new Rectangle(200, 320, 110, 60);
	public Rectangle hardButton = new Rectangle(210, 470, 80, 65);

	public Difficulty() {

		BufferedImageLoader loader = new BufferedImageLoader();
		menuImg = loader.loadImage(menuPath);

	}

	public void render(Graphics g) {

		g.drawImage(menuImg, 0, 0, null);
	}

}