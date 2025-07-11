package main;

import javax.swing.*;

public class Main extends JFrame{
    private static int windowWidth = 1000;
    private static int windowHeight = 600;
    private static JFrame mainFrame = new JFrame("AdvantureGame");
    private static StartPanel startPanel = new StartPanel();
    private static GamePanel gamePanel = new GamePanel();
    private static GameOverPanel gameOverPanel = new GameOverPanel();
    public static GameState gameState = GameState.START;

    public static void main (String[] args) {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.setSize(windowWidth, windowHeight);
        mainFrame.setLocationRelativeTo(null);

        switchGameState();
    }

    private static void switchGameState() {
        mainFrame.getContentPane().removeAll();

        if (gameState == GameState.START) {
            mainFrame.add(startPanel);
        }

        if (gameState == GameState.PLAY) {
            mainFrame.add(gamePanel);
            gamePanel.requestFocusInWindow();
        }

        if (gameState == GameState.GAME_OVER) {
            mainFrame.add(gameOverPanel);
        }

        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.setVisible(true);
    }

    public static void switchToGamePanel() {
        gameState = GameState.PLAY;
        switchGameState();
    }

    public static void switchToGameOverPanel() {
        gameState = GameState.GAME_OVER;
        switchGameState();
    }

    public static int getWindowWidth() {
        return windowWidth;
    }

    public static int getWindowHeight() {
        return windowHeight;
    }
}
