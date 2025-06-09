package tile;

import main.Camera;

import javax.swing.*;
import java.awt.*;

public class DirtFive {
    private final Image dirtImage;
    private final int dirtX;
    private final int dirtY;
    private final int dirtSize;

    public DirtFive(int dirtX, int dirtY, int dirtSize) {
        dirtImage = new ImageIcon("res/tiles/5.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        this.dirtX = dirtX;
        this.dirtY = dirtY;
        this.dirtSize = dirtSize;
    }

    public void draw(Graphics g, Camera camera) {
        g.drawImage(dirtImage, dirtX - (int)camera.getX(), dirtY - (int)camera.getY(), dirtSize, dirtSize, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(dirtX, dirtY, dirtSize, dirtSize);
    }
}
