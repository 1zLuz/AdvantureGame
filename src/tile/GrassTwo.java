package tile;

import main.Camera;

import javax.swing.*;
import java.awt.*;

public class GrassTwo {
    private final Image grassImage;
    private final int grassX;
    private final int grassY;
    private final int grassSize;

    public GrassTwo(int grassX, int grassY, int grassSize) {
        grassImage = new ImageIcon("res/tiles/2.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        this.grassX = grassX;
        this.grassY = grassY;
        this.grassSize = grassSize;
    }

    public void draw(Graphics g, Camera camera) {
        g.drawImage(grassImage, grassX - (int)camera.getX(), grassY - (int)camera.getY(), grassSize, grassSize, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(grassX, grassY, grassSize, grassSize);
    }
}
