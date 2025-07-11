package main;

import entity.Player;
import tile.*;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements Runnable {
    private final Player player = new Player(0,(7 * 64) - 100);
    private final KeyHandler keyHandler = new KeyHandler();
    private final Image backGroundImage = new ImageIcon("res/tiles/background.png").getImage();
    private final int FPS = 60;
    private Thread thread;
    public static final int tileSize = 64;
    private final int mapX = tileSize * 120;
    private final int mapY = tileSize * 20;
    private final Camera camera = new Camera(0, 0, mapX, mapY);

    // Block Lists
    // Grass
    private final List<GrassOne> grassOneList = new ArrayList<>();
    private final List<GrassTwo> grassTwoList = new ArrayList<>();
    private final List<GrassThree> grassThreeList = new ArrayList<>();
    // Dirt
    private final List<DirtFour> dirtFourList = new ArrayList<>();
    private final List<DirtFive> dirtFiveList = new ArrayList<>();
    private final List<DirtSix> dirtSixList = new ArrayList<>();
    // Water
    private final List<WaterEightteen> waterEightteenList = new ArrayList<>();

    public GamePanel() {
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        loadMap("res/maps/map1.txt");
        startGameThread();
        this.requestFocusInWindow();
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
                        grassOneList.add(new GrassOne(x * tileSize, y * tileSize, tileSize));
                    }
                    if (c == '2') {
                        grassTwoList.add(new GrassTwo(x * tileSize, y * tileSize, tileSize));
                    }
                    if (c == '3') {
                        grassThreeList.add(new GrassThree(x * tileSize, y * tileSize, tileSize));
                    }
                    if (c == '4') {
                        dirtFourList.add(new DirtFour(x * tileSize, y * tileSize, tileSize));
                    }
                    if (c == '5') {
                        dirtFiveList.add(new DirtFive(x * tileSize, y * tileSize, tileSize));
                    }
                    if (c == '6') {
                        dirtSixList.add(new DirtSix(x * tileSize, y * tileSize, tileSize));
                    }
                    if (c == '9') {
                        waterEightteenList.add(new WaterEightteen(x * tileSize, y * tileSize, tileSize));
                    }
                }
                y++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isColliding(Rectangle rectangle) {
        for (GrassOne grassOne : grassOneList) {
            if (grassOne.getBounds().intersects(rectangle)) {
                return true;
            }
        }
        for (GrassTwo grassTwo : grassTwoList) {
            if (grassTwo.getBounds().intersects(rectangle)) {
                return true;
            }
        }
        for (GrassThree grassThree : grassThreeList) {
            if (grassThree.getBounds().intersects(rectangle)) {
                return true;
            }
        }

        for (DirtFour dirtFour : dirtFourList) {
            if (dirtFour.getBounds().intersects(rectangle)) {
                return true;
            }
        }

        for (DirtFive dirtFive: dirtFiveList) {
            if (dirtFive.getBounds().intersects(rectangle)) {
                return true;
            }
        }

        for (DirtSix dirtSix: dirtSixList) {
            if (dirtSix.getBounds().intersects(rectangle)) {
                return true;
            }
        }
        return false;
    }

    private void updatePlayerPosition() {
        int speed = player.getPlayerSpeed();
        int width = player.getPlayerWidth();
        int height = player.getPlayerHeight();

        if (keyHandler.leftPressed) {
            if (player.playerX >= 0) {
                player.playerX -= speed;
            }
        }
        if (keyHandler.rightPressed) {
            if (player.playerX + width <= mapX) {
                player.playerX += speed;
            }
        }
        if(keyHandler.spacePressed && player.isOnGround) {
            player.velocityY = player.getJumpStrength();
            player.isOnGround = false;
        }
        player.velocityY += player.gravity;
        float nextY = player.playerY + player.velocityY;
        Rectangle futureBounds = new Rectangle(player.playerX, (int)nextY, width, height);
        if (player.velocityY > 0) {
            if (isColliding(futureBounds)) {
                player.velocityY = 0;
                player.isOnGround = true;

                while (!isColliding(new Rectangle(player.playerX, player.playerY + 1, width, height))) {
                    player.playerX += 1;
                }
            } else {
                player.playerY += (int) player.velocityY;
                player.isOnGround = false;
            }
        } else if (player.velocityY < 0) {
            if (!isColliding(futureBounds)) {
                player.playerY += (int) player.velocityY;
                player.isOnGround = false;
            } else {
                player.velocityY = 0;
            }
        }

        if (player.playerY > mapY) {
            player.health = 0;
        }
    }

    private boolean checkIfDead() {
        if (player.health == 0) {
            return true;
        }
        return false;
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
        checkIfDead();
        checkGameOver();
        camera.centerOnEntity(player);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backGroundImage, 0, 0, null);

        for (GrassOne grassOne : grassOneList) {
            grassOne.draw(g, camera);
        }

        for (GrassTwo grassTwo : grassTwoList) {
            grassTwo.draw(g, camera);
        }

        for (GrassThree grassThree : grassThreeList) {
            grassThree.draw(g, camera);
        }

        for (DirtFour dirtFour : dirtFourList) {
            dirtFour.draw(g, camera);
        }

        for (DirtFive dirtFive : dirtFiveList) {
            dirtFive.draw(g, camera);
        }

        for (DirtSix dirtSix : dirtSixList) {
            dirtSix.draw(g, camera);
        }

        for (WaterEightteen waterEightteen : waterEightteenList) {
            waterEightteen.draw(g, camera);
        }

        player.draw(g,camera);
    }

    public void checkGameOver() {
        if (checkIfDead()) {
            Main.switchToGameOverPanel();
        }
    }
}
