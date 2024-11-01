package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    private final int originalTileSize = 16;
    private final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;

    private final int screenWidth = tileSize * maxScreenCol;
    private final int screenHeight = tileSize * maxScreenRow;

    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    private int playerX = 100;
    private int playerY = 100;
    private int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        startGameThread();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = (double) 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() / drawInterval;

        while (gameThread != null){
            update();
            repaint();
        }

        try {
            double remainingTime = drawInterval - System.nanoTime();
            remainingTime /= 1000000;

            if (remainingTime < 0) {
                remainingTime = 0;
            }

            Thread.sleep((long) remainingTime);

            nextDrawTime += drawInterval;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void update() {
        if (keyH.leftPressed == true) {
            playerX = playerX - playerSpeed;
        }
        if (keyH.rightPressed == true) {
            playerX = playerX + playerSpeed;
        }
        if (keyH.upPressed == true) {
            playerY = playerY - playerSpeed;
        }
        if (keyH.downPressed == true) {
            playerY = playerY + playerSpeed;
        }
    }
    public void paint(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.WHITE);
        g2d.fillRect(playerX, playerY, tileSize, tileSize);

        g2d.dispose();
    }
}
