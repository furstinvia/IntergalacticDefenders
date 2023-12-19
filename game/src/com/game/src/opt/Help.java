package com.game.src.opt;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.game.src.loader.BufferedImageLoader;

public class Help {

	public Rectangle backButton = new Rectangle(349, 527, 106, 68);
	BufferedImage backImg;
	String backPath = "/newhelp.png";

	public Help() {
		BufferedImageLoader loader = new BufferedImageLoader();
		backImg = loader.loadImage(backPath);
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Font font = new Font("arial", Font.BOLD, 50);
		g.setFont(font);
		g.setColor(Color.white);

		g.drawImage(backImg, 0, 0, null);
		// (g2d).draw(backButton);
	}
}
