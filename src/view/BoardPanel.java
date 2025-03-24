package view;

import javax.swing.*;
import java.awt.*;
import model.board.*;

public class BoardPanel extends JPanel {
    private JButton[][] tileButtons;
    private Board board;

    public BoardPanel(){
        this.setLayout(new GridLayout(7, 9));

        initializeBoard();
    }

    private void initializeBoard(){
        for (int row = 0; row < board.getMaxRow(); row++){
            for (int col = 0; col < board.getMaxCol(); col++){
                JButton button = new JButton();
                button.setBackground(Color.WHITE);
                this.tileButtons[row][col] = button;
                this.add(button);
            }
        }
    }
}