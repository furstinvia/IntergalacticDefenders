package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;

import javax.imageio.ImageIO;

import com.game.src.loader.BufferedImageLoader;
import com.game.src.main.Game.DIFFICULTY;

public class Tank extends GameObject implements EntityA {

	private BufferedImage tankImg;
	private String tankPath = "/tankserem.png";
	private Controller controller;

	private double vx = 0;
	private double vy = 0;
	private int health = 100;

	public Tank(double x, double y, Controller controller) {

		super(x, y);
		this.controller = controller;

		BufferedImageLoader loader = new BufferedImageLoader();
		tankImg = loader.loadImage(tankPath);

	}

	public void tick() {

		x += vx;
		y += vy;

		// Check Outer Border
		if (x <= Game.GRASS) {
			x = Game.GRASS;
		}

		if (y <= 0) {
			y = 0;
		}

		if (x >= Main.WIDTH - tankImg.getWidth() - Game.GRASS) {
			x = Main.WIDTH - tankImg.getWidth() - Game.GRASS;
		}

		if (y >= Main.HEIGHT - tankImg.getHeight()) {
			y = Main.HEIGHT - tankImg.getHeight();
		}

		// Check Collision between Enemy and Tank
		for (int i = 0; i < controller.getEntitiesBSize(); i++) {

			EntityB tempEntityB = controller.getEntitiesB().get(i);

			if (Physics.Collision(this, tempEntityB)) {

				health -= ((Enemy) tempEntityB).getAttPoint();
				controller.removeEntity(tempEntityB);
				System.out.println("Health " + health);
			}
		}
	}

	public void render(Graphics g) {
		g.drawImage(tankImg, (int) x, (int) y, null);
	}

	public void reset() {
		setX(218);
		setY(300);
		setvx(0);
		setvy(0);
		setHealth(100);
	}

	// Getters and Setters
	public void setvx(double vx) {
		this.vx = vx;
	}

	public void setvy(double vy) {
		this.vy = vy;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, tankImg.getWidth(), tankImg.getHeight());
	}

	public int getHealth() {
		return health;
	}

}
