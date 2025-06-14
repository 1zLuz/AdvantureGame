package entity;

import main.Camera;

import javax.swing.*;
import java.awt.*;

public class Player {
    private final int playerWidth = 50;
    private final int playerHeight = 100;
    public int playerX = 0;
    public int playerY = 470;
    public boolean isOnGround = false;
    public float gravity = 0.5f;
    public float velocityY = 0;
    private final int playerSpeed = 10;
    private final float jumpStrength = -16f;
    private final Image playerImage;
    public int health = 100;

    public Player(int x, int y) {
        playerImage = new ImageIcon("res/player/playerimage.png").getImage().getScaledInstance(50,100, Image.SCALE_DEFAULT);
        playerX = x;
        playerY = y;
    }

    public void draw(Graphics g, Camera camera) {
        int screenX = playerX - (int)camera.getX();
        int screenY = playerY - (int)camera.getY();

        g.drawImage(playerImage, screenX, screenY, null);
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

    public float getJumpStrength() { return jumpStrength; }
}
