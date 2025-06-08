package entity;

import javax.swing.*;
import java.awt.*;

public class Player {
    private final int playerWidth = 200;
    private final int playerHeight = 550;
    public int playerX = 0;
    public int playerY = 300;
    private final int playerSpeed = 10;
    private final Image playerImage;

    public Player() {
        playerImage = new ImageIcon("res/player/playerimage.png").getImage().getScaledInstance(50,100, Image.SCALE_DEFAULT);
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(playerImage, playerX, playerY, null);
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

    public Rectangle getBounds() {
        return new Rectangle(playerX, playerY, playerWidth, playerHeight);
    }
}
