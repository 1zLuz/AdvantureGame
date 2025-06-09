package entity;

import main.Camera;
import main.GamePanel;
import main.Main;

import javax.swing.*;
import java.awt.*;

public class Player {
    private final int playerWidth = 200;
    private final int playerHeight = 550;
    private final int screenX = Main.getWindowWidth() / 2 - (GamePanel.tileSize / 2);
    private final int screenY = Main.getWindowHeight() / 2 - (GamePanel.tileSize / 2);
    public int playerX = 0;
    public int playerY = 470;
    private final int playerSpeed = 10;
    private final Image playerImage;

    public Player() {
        playerImage = new ImageIcon("res/player/playerimage.png").getImage().getScaledInstance(50,100, Image.SCALE_DEFAULT);
    }

    public void draw(Graphics g, Camera camera) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(playerImage, screenX, screenY, null);
    }

    public int getPlayerSpeed() {
        return playerSpeed;
    }

    public int getPlayerWidth() {
        return playerWidth;
    }

    public int getPlayerHeight() {
        return playerHeight;
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public Rectangle getBounds() {
        return new Rectangle(playerX, playerY, playerWidth, playerHeight);
    }
}
