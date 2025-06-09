package main;

import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel {
    private final Image backGroundImage;
    private final JLabel title = new JLabel("Welcome to the AdventureGame");
    private final JButton startButton = new JButton("Start Game");

    public StartPanel() {
        backGroundImage = new ImageIcon("res/tiles/background.png").getImage();
        createLayout();
        startButtonListener();
    }

    private void createLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 5, 10, 5);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        this.add(title, c);

        c.gridy = 1;
        this.add(startButton, c);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backGroundImage, 0, 0, null);
    }

    private void startButtonListener() {startButton.addActionListener(e -> Main.switchToGamePanel());}
}