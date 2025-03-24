package view;

import javax.swing.*;

public class GamePanel extends JPanel {
    private JLabel title;

    public GamePanel(){
        this.title = new JLabel("JUNGLE KING"); // insert image

        this.add(title);
    }
}