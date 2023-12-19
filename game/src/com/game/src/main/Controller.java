package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.game.src.loader.BufferedImageLoader;
import com.game.src.main.Game.DIFFICULTY;
import com.game.src.main.Game.STATE;

public class Controller extends JPanel {

	private LinkedList<EntityA> entitiesA = new LinkedList<EntityA>();
	private LinkedList<EntityB> entitiesB = new LinkedList<EntityB>();

	private Tank tank;
	private EntityA entityA;
	private EntityB entityB;

	private int spawn_count = 0;
	private int spawn_killed = 0;
	private int enemy_killed;

	private BufferedImage backImg;
	private String backPath = "/lovespace.jpeg";

	Random rand = new Random();

	public Controller() {

		tank = new Tank(246, 602, this);

		BufferedImageLoader loader = new BufferedImageLoader();
		backImg = loader.loadImage(backPath);
	}

	public void tick() {

		if (tank.getHealth() == 0) {
			Game.state = STATE.GAMEOVER;
		}

		if (enemy_killed == 0) {
			Game.state = STATE.WIN;
		}

		tank.tick();

		for (int i = 0; i < entitiesA.size(); i++) {
			entityA = entitiesA.get(i);

			entityA.tick();
		}

		for (int i = 0; i < entitiesB.size(); i++) {
			entityB = entitiesB.get(i);

			entityB.tick();
		}

		if (spawn_killed == 5) {
			spawn_killed = 0;
			spawn_count = 0;
			addEnemy(5);
		}

	}

	public void render(Graphics g) {

		g.drawImage(backImg, 0, 0, null);

		tank.render(g);

		// Health Bar
		g.setColor(Color.WHITE);
		g.fillRect(10, 10, tank.getHealth() * 2, 20);

		// Teks informasi
		Font font = new Font("Arial", Font.BOLD, 20);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("Heart Left: " + enemy_killed, 10, 50);
		for (int i = 0; i < entitiesA.size(); i++) {

			entityA = entitiesA.get(i);
			entityA.render(g);
		}

		for (int i = 0; i < entitiesB.size(); i++) {

			entityB = entitiesB.get(i);
			entityB.render(g);
		}

	}

	public void start() {

		if (Game.diff == DIFFICULTY.EASY) {

			enemy_killed = 200;
			addEnemy(10);

		} else if (Game.diff == DIFFICULTY.MEDIUM) {

			enemy_killed = 100;
			addEnemy(10);

		} else if (Game.diff == DIFFICULTY.HARD) {

			enemy_killed = 100;
			addEnemy(10);

		}

	}

	public void reset() {

		entitiesA.removeAll(entitiesA);
		entitiesB.removeAll(entitiesB);
		this.spawn_count = 0;
		this.spawn_killed = 0;
		this.enemy_killed = -1;
		tank.reset();

	}

	public void pause() {
		tank.setvx(0);
		tank.setvy(0);
	}

	public void addEnemy(int spawn_count) {
		for (int i = 0; i < spawn_count; i++) {

			addEntity(new Enemy(rand.nextInt((Main.WIDTH - Game.GRASS - Game.GRASS - 64)) + Game.GRASS,
					rand.nextInt(136) - 200, this));

			this.spawn_count++;
		}
	}

	public void addEntity(EntityA e) {
		entitiesA.add(e);
	}

	public void removeEntity(EntityA e) {
		entitiesA.remove(e);
	}

	public void addEntity(EntityB e) {
		entitiesB.add(e);
	}

	public void removeEntity(EntityB e) {
		entitiesB.remove(e);
		spawn_killed += 1;
	}

	public void countKilled() {
		enemy_killed--;
	}

	// Getters
	public Tank getTank() {
		return tank;
	}

	public LinkedList<EntityA> getEntitiesA() {
		return entitiesA;
	}

	public LinkedList<EntityB> getEntitiesB() {
		return entitiesB;
	}

	public int getEntitiesASize() {
		return entitiesA.size();
	}

	public int getEntitiesBSize() {
		return entitiesB.size();
	}
}
