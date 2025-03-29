package view;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    private JLabel playerTurnLbl;

    public InfoPanel(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(200,600));
    }
}