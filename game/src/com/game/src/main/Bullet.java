package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.game.src.loader.BufferedImageLoader;

public class Bullet extends GameObject implements EntityA {

	private BufferedImage bulletImg;
	private String bulletPath = "/shot.gif";

	private Controller controller;

	public Bullet(double x, double y, Controller controller) {

		super(x + 15, y - 21);
		this.controller = controller;

		BufferedImageLoader loader = new BufferedImageLoader();
		bulletImg = loader.loadImage(bulletPath);

	}

	public void tick() {
		y -= 10;

		for (int i = 0; i < controller.getEntitiesBSize(); i++) {
			if (Physics.Collision(this, controller.getEntitiesB().get(i))) {
				System.out.println("collision");
			}
		}
	}

	public void render(Graphics g) {
		g.drawImage(bulletImg, (int) x, (int) y, null);
	}

	// Getter
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, bulletImg.getWidth(), bulletImg.getHeight());
	}

}
