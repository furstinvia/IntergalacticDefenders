package com.game.src.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.IllegalFormatCodePointException;
import java.util.LinkedList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

import com.game.src.opt.Difficulty;
import com.game.src.opt.GameOver;
import com.game.src.opt.Help;
import com.game.src.opt.Menu;
import com.game.src.opt.Pause;

public class Game extends Canvas implements Runnable {

	private boolean running = false;
	private Thread thread;

	private Controller controller;
	private Menu menu;
	private Difficulty difficulty;
	private GameOver gameOver;
	private Win win;
	private Pause pause;
	private Help help;

	// Grass right-left area size : 70px each
	public static final int GRASS = 70;

	public static enum STATE {
		MENU,
		GAME,
		DIFF,
		GAMEOVER,
		WIN,
		PAUSE,
		HELP
	};

	public static enum DIFFICULTY {
		BASE,
		EASY,
		MEDIUM,
		HARD
	};

	public static STATE state = STATE.MENU;
	public static DIFFICULTY diff = DIFFICULTY.BASE;

	public void init() {

		// Audio Player Initialization

		// Menu Initialization
		menu = new Menu();
		difficulty = new Difficulty();
		gameOver = new GameOver();
		win = new Win();
		pause = new Pause();
		help = new Help();

		// Controller Initialization
		controller = new Controller();

		// Key and Mouse Listener Initialization
		requestFocus();
		addKeyListener(new KeyboardPanel(controller.getTank(), controller));
		addMouseListener(new MousePanel(menu, difficulty, gameOver, win, pause, help, controller));
	}

	public synchronized void start() {
		if (running) {
			return;
		}

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	private synchronized void stop() {
		if (!running) {
			return;
		}

		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}

	@Override
	public void run() {

		init();

		// FPS
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			if (delta >= 1) {
				tick();
				updates++;
				delta--;
			}

			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " Ticks, Fps " + frames);
				updates = 0;
				frames = 0;
			}
		}

		stop();
	}

	private void tick() {

		if (state == STATE.GAME && diff != DIFFICULTY.BASE)
			controller.tick();

	}

	private void render() {

		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		/////////////////////////////////

		switch (state) {
			case DIFF:
				difficulty.render(g);
				break;
			case MENU:
				menu.render(g);
				break;
			case GAME:
				controller.render(g);
				break;
			case GAMEOVER:
				gameOver.render(g);
				break;
			case WIN:
				win.render(g);
				break;
			case PAUSE:
				pause.render(g);
				break;
			case HELP:
				help.render(g);
				break;
			default:
				menu.render(g);
				break;
		}

		/////////////////////////////////
		g.dispose();
		bs.show();
	}

}
