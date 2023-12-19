package com.game.src.opt;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.game.src.loader.BufferedImageLoader;

public class Pause {

	BufferedImage pauseImg;
	String pausePath = "/newpause.png";

	public Rectangle continueButton = new Rectangle(145, 188, 211, 80);
	public Rectangle menuButton = new Rectangle(175, 310, 135, 80);

	public Pause() {
		BufferedImageLoader loader = new BufferedImageLoader();
		pauseImg = loader.loadImage(pausePath);
	}

	public void render(Graphics g) {
		g.drawImage(pauseImg, 0, 0, null);
	}
}
