package tile;

import main.Camera;

import javax.swing.*;
import java.awt.*;

public class WaterEightteen {
    private final Image waterImage;
    private final int waterX;
    private final int waterY;
    private final int waterSize;

    public WaterEightteen(int waterX, int waterY, int waterSize) {
        waterImage = new ImageIcon("res/tiles/18.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        this.waterX = waterX;
        this.waterY = waterY;
        this.waterSize = waterSize;
    }

    public void draw(Graphics g, Camera camera) {
        g.drawImage(waterImage, waterX - (int)camera.getX(), waterY - (int)camera.getY(), waterSize, waterSize, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(waterX, waterY, waterSize, waterSize);
    }
}
