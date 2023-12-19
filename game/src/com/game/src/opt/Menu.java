package com.game.src.opt;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.game.src.loader.BufferedImageLoader;

public class Menu {

	BufferedImage menuImg;
	String menuPath = "/pressstart.png";

	public Rectangle playButton = new Rectangle(190, 160, 130, 70);
	public Rectangle helpButton = new Rectangle(190, 310, 130, 70);
	public Rectangle quitButton = new Rectangle(190, 465, 130, 70);

	public Menu() {
		BufferedImageLoader loader = new BufferedImageLoader();
		menuImg = loader.loadImage(menuPath);
	}

	public void render(Graphics g) {

		g.drawImage(menuImg, 0, 0, null);
	}
}
