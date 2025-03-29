package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TitleScreen extends JFrame {
    private JButton startBtn;

    public TitleScreen(ActionListener startListener){
        this.setTitle("Jungle King");
        this.setSize(700, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Jungle King");
        this.add(titleLabel, BorderLayout.CENTER);

        this.startBtn = new JButton("START");
        this.startBtn.setFont(new Font("Arial", Font.BOLD, 18));
        this.startBtn.setFocusPainted(false);
        this.startBtn.addActionListener(startListener);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startBtn);
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.setLocationRelativeTo(null);
    }

    public void showScreen(){
        setVisible(true);
    }

    public void hideScreen(){
        setVisible(false);
    }
}