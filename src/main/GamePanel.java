package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    private final Player player = new Player();
    private final KeyHandler keyHandler = new KeyHandler();
    int FPS = 60;
    Thread thread;

    public GamePanel() {
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        startGameThread();
    }

    public void startGameThread() {
        thread = new Thread(this);
        thread.start();
    }

    private void updatePlayerPosition() {
        if (keyHandler.upPressed) {
            player.playerY -= player.getPlayerSpeed();
        }
        if (keyHandler.downPressed) {
            player.playerY += player.getPlayerSpeed();
        }
        if (keyHandler.leftPressed) {
            player.playerX -= player.getPlayerSpeed();
        }
        if (keyHandler.rightPressed) {
            player.playerX += player.getPlayerSpeed();
        }
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (thread.isAlive()) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        updatePlayerPosition();
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        player.draw(g);
    }
}
