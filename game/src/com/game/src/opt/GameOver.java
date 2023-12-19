package com.game.src.opt;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.game.src.loader.BufferedImageLoader;

public class GameOver {

	BufferedImage gameOverImg;
	String gameOverPath = "/newgameover.png";

	public Rectangle tryAgainButton = new Rectangle(122, 266, 257, 78);
	public Rectangle menuButton = new Rectangle(159, 392, 190, 87);

	public GameOver() {
		BufferedImageLoader loader = new BufferedImageLoader();
		gameOverImg = loader.loadImage(gameOverPath);
	}

	public void render(Graphics g) {
		g.drawImage(gameOverImg, 0, 0, null);
	}
}
