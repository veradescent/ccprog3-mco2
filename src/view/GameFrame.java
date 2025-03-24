package view;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public GameFrame(){
        this.setTitle("Jungle King");
        this.setSize(700, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        GamePanel gamePanel = new GamePanel();
        BoardPanel boardPanel = new BoardPanel();
        this.add(gamePanel, BorderLayout.NORTH);
        this.add(boardPanel, BorderLayout.CENTER);
    }

    public void showWinner(String winner){
        JOptionPane.showMessageDialog(this, winner + " wins the game!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }
}