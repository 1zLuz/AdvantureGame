package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GameOverPanel extends JPanel {
    private final Image backgroundImage;
    private final JButton restartButton = new JButton("Restart");

    public GameOverPanel() {
        backgroundImage = new ImageIcon("res/tiles/background.png").getImage();
        createLayout();
        addRestartButtonListener();
    }

    private void createLayout() {
        JLabel gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 40));

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5, 10, 5, 10);
        this.add(gameOverLabel, c);

        c.gridy = 1;
        c.insets = new Insets(5, 10, 5, 10);
        this.add(restartButton, c);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, null);
    }

    private void addRestartButtonListener() {
        restartButton.addActionListener(e -> {
            Main.switchToGamePanel();
        });
    }
}
