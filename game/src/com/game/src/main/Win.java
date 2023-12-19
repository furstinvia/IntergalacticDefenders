package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.game.src.loader.BufferedImageLoader;

public class Win {

	BufferedImage winImg;
	String winPath = "/newbgtmenu.png";
	public Rectangle menuButton = new Rectangle(194, 360, 125, 65);

	public Win() {
		BufferedImageLoader loader = new BufferedImageLoader();
		winImg = loader.loadImage(winPath);
	}

	public void render(Graphics g) {
		g.drawImage(winImg, 0, 0, null);
	}

}