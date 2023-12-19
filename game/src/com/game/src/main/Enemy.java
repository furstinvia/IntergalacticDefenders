package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.tree.FixedHeightLayoutCache;

import com.game.src.loader.BufferedImageLoader;
import com.game.src.main.Game.DIFFICULTY;

public class Enemy extends GameObject implements EntityB {

    private BufferedImage enemyImg;
    private String enemyPath = "/lovetry.png";

    private Random rand = new Random();
    private Controller controller;

    private int speed = 0; // Monster Speed
    private int hp = 0; // Monster Health Point
    private int attPoint = 0; // Monster Attack Point

    public Enemy(double x, double y, Controller controller) {

        super(x, y);
        this.controller = controller;

        BufferedImageLoader loader = new BufferedImageLoader();

        if (Game.diff == DIFFICULTY.EASY) {
            enemyImg = loader.loadImage(enemyPath, 226, 295, 58, 64);
            speed = 2;
            hp = 1;
            attPoint = 10;
        } else if (Game.diff == DIFFICULTY.MEDIUM) {
            enemyImg = loader.loadImage(enemyPath, 226, 295, 58, 64);
            speed = 3;
            hp = 3;
            attPoint = 20;
        } else if (Game.diff == DIFFICULTY.HARD) {
            enemyImg = loader.loadImage(enemyPath, 226, 295, 58, 64);
            speed = 4;
            hp = 3;
            attPoint = 25;
        }
    }

    public void tick() {
        y += speed;

        if (y > Main.HEIGHT) {
            y = rand.nextInt(136) - 200;
            x = rand.nextInt(Main.WIDTH - Game.GRASS - Game.GRASS - 64) + Game.GRASS;
        }

        // Check Collision between Enemy and Bullet
        for (int i = 0; i < controller.getEntitiesASize(); i++) {
            EntityA tempEntityA = controller.getEntitiesA().get(i);

            if (Physics.Collision(this, tempEntityA)) {
                hp--;
                if (hp == 0) {
                    controller.removeEntity(this);
                    controller.countKilled();
                }
                controller.removeEntity(tempEntityA);

            }
        }

    }

    public void render(Graphics g) {
        g.drawImage(enemyImg, (int) x, (int) y, null);
    }

    // Getters
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, enemyImg.getWidth(), enemyImg.getHeight());
    }

    public int getAttPoint() {
        return attPoint;
    }

}
