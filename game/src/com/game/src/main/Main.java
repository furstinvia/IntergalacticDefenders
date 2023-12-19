package com.game.src.main;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.game.src.loader.SoundHandler;

public class Main {

	public static final int WIDTH = 500;
	public static final int HEIGHT = 640;
	public static final String TITLE = "Intergalactic Defenders";

	public static void main(String[] args) {
		Game game = new Game();

		game.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		JFrame frame = new JFrame(TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();

		SoundHandler.RunMusic("res/mario.wav");

	}

}
