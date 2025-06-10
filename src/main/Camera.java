package main;

import entity.Player;

public class Camera {
    private float x, y;
    private int mapX, mapY;

    public Camera(float x, float y, int mapX, int mapY) {
        this.x = x;
        this.y = y;
        this.mapX = mapX;
        this.mapY = mapY;
    }

    public void centerOnEntity(Player player) {
        float targetX = player.getPlayerX() + player.getPlayerWidth() / 2f - Main.getWindowWidth() / 2f;
        float targetY = player.getPlayerY() + player.getPlayerHeight() / 2f - Main.getWindowHeight() / 2f;

        if (targetX < 0) {
            targetX = 0;
        } else if (targetX > mapX - Main.getWindowWidth()) {
            targetX = mapX - Main.getWindowWidth();
        }

        if (targetY < 0) {
            targetY = 0;
        } else if (targetY > mapY - Main.getWindowHeight()) {
            targetY = mapY - Main.getWindowHeight();
        }

        this.x = targetX;
        this.y = targetY;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
