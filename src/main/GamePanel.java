package main;

import entity.Player;
import tile.GrassTwo;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements Runnable {
    private final Player player = new Player();
    private final KeyHandler keyHandler = new KeyHandler();
    private final Image backGroundImage = new ImageIcon("res/tiles/background.png").getImage();
    private final int FPS = 60;
    private Thread thread;
    private List<GrassTwo> grassTwoList = new ArrayList<>();
    private final int tileSize = 64;

    public GamePanel() {
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        loadMap("res/maps/map1.txt");
        startGameThread();
    }

    public void startGameThread() {
        thread = new Thread(this);
        thread.start();
    }

    private void loadMap(String levelName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(levelName))) {
            String line;
            int y = 0;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                for (int x = 0; x < line.length(); x++) {
                    char c = line.charAt(x);
                    if (c == '1') {
                        grassTwoList.add(new GrassTwo(x * tileSize, y * tileSize, tileSize));
                    }
                }
                y++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backGroundImage, 0, 0, null);

        for (GrassTwo grassTwo : grassTwoList) {
            grassTwo.draw(g);
        }

        player.draw(g);
    }
}
