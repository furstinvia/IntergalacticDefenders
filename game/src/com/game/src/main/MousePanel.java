package com.game.src.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.game.src.main.Game.DIFFICULTY;
import com.game.src.main.Game.STATE;
import com.game.src.opt.Difficulty;
import com.game.src.opt.GameOver;
import com.game.src.opt.Help;
import com.game.src.opt.Menu;
import com.game.src.opt.Pause;

public class MousePanel implements MouseListener {

	private Menu menu;
	private Difficulty difficulty;
	private GameOver gameOver;
	private Controller controller;
	private Win win;
	private Pause pause;
	private Help help;

	public MousePanel(Menu menu, Difficulty difficulty, GameOver gameOver, Win win, Pause pause, Help help,
			Controller controller) {
		this.menu = menu;
		this.difficulty = difficulty;
		this.gameOver = gameOver;
		this.controller = controller;
		this.win = win;
		this.pause = pause;
		this.help = help;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {

		int px = e.getX();
		int py = e.getY();

		// Menu State
		if (Game.state == STATE.MENU) {
			if (px >= menu.playButton.getMinX() && px <= menu.playButton.getMaxX()) {
				if (py >= menu.playButton.getMinY() && py <= menu.playButton.getMaxY()) {
					Game.state = STATE.DIFF;
				}
			}

			if (px >= menu.helpButton.getMinX() && px <= menu.helpButton.getMaxX()) {
				if (py >= menu.helpButton.getMinY() && py <= menu.helpButton.getMaxY()) {
					Game.state = STATE.HELP;
				}
			}

			if (px >= menu.quitButton.getMinX() && px <= menu.quitButton.getMaxX()) {
				if (py >= menu.quitButton.getMinY() && py <= menu.quitButton.getMaxY()) {
					System.exit(1);
				}
			}
		}

		// Diff State
		else if (Game.state == STATE.DIFF) {
			if (px >= difficulty.easyButton.getMinX() && px <= difficulty.easyButton.getMaxX()) {
				if (py >= difficulty.easyButton.getMinY() && py <= difficulty.easyButton.getMaxY()) {
					Game.state = STATE.GAME;
					Game.diff = DIFFICULTY.EASY;
					controller.start();
				}
			}

			if (px >= difficulty.mediumButton.getMinX() && px <= difficulty.mediumButton.getMaxX()) {
				if (py >= difficulty.mediumButton.getMinY() && py <= difficulty.mediumButton.getMaxY()) {
					Game.state = STATE.GAME;
					Game.diff = DIFFICULTY.MEDIUM;
					controller.start();
				}
			}

			if (px >= difficulty.hardButton.getMinX() && px <= difficulty.hardButton.getMaxX()) {
				if (py >= difficulty.hardButton.getMinY() && py <= difficulty.hardButton.getMaxY()) {
					Game.state = STATE.GAME;
					Game.diff = DIFFICULTY.HARD;
					controller.start();
				}
			}
		}
		// Game Over State
		else if (Game.state == STATE.GAMEOVER) {
			if (px >= gameOver.tryAgainButton.getMinX() && px <= gameOver.tryAgainButton.getMaxX()) {
				if (py >= gameOver.tryAgainButton.getMinY() && py <= gameOver.tryAgainButton.getMaxY()) {
					controller.reset();
					Game.state = STATE.GAME;
					controller.start();
				}
			}

			if (px >= gameOver.menuButton.getMinX() && px <= gameOver.menuButton.getMaxX()) {
				if (py >= gameOver.menuButton.getMinY() && py <= gameOver.menuButton.getMaxY()) {
					Game.state = STATE.MENU;
					Game.diff = DIFFICULTY.BASE;
					controller.reset();
				}
			}

		}
		// Win State
		else if (Game.state == STATE.WIN) {
			if (px >= win.menuButton.getMinX() && px <= win.menuButton.getMaxX()) {
				if (py >= win.menuButton.getMinY() && py <= win.menuButton.getMaxY()) {
					Game.state = STATE.MENU;
					Game.diff = DIFFICULTY.BASE;
					controller.reset();
				}
			}
		} else if (Game.state == STATE.PAUSE) {
			if (px >= pause.continueButton.getMinX() && px <= pause.continueButton.getMaxX()) {
				if (py >= pause.continueButton.getMinY() && py <= pause.continueButton.getMaxY()) {
					Game.state = STATE.GAME;
				}
			}
			if (px >= pause.menuButton.getMinX() && px <= pause.menuButton.getMaxX()) {
				if (py >= pause.menuButton.getMinY() && py <= pause.menuButton.getMaxY()) {
					Game.state = STATE.MENU;
					Game.diff = DIFFICULTY.BASE;
					controller.reset();
				}
			}
		} else if (Game.state == STATE.HELP) {
			if (px >= help.backButton.getMinX() && px <= help.backButton.getMaxX()) {
				if (py >= help.backButton.getMinY() && py <= help.backButton.getMaxY()) {
					Game.state = STATE.MENU;
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
