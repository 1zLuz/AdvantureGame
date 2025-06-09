package main;

import entity.Player;

public class Camera {
    private float x, y;

    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void centerOnEntity(Player player) {
        this.x = player.getPlayerX() - (float) Main.getWindowWidth() / 2 + (float) player.getPlayerWidth() / 2;
        this.y = player.getPlayerY() - (float) Main.getWindowHeight() / 2 + (float) player.getPlayerHeight() / 2;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
