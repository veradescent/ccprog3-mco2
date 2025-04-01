package view;

import javax.swing.*;
import java.awt.*;

/**
 * This class serves as the view for the information details of the game
 */
public class InfoPanel extends JPanel {
    /**
     * Constructs an initial view of the InfoPanel
     */
    public InfoPanel(){
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(200,100));
    }
}