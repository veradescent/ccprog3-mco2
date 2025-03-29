package view;

import model.board.Board;

import java.awt.GridLayout;
import javax.swing.*;

public class BoardPanel extends JPanel {
    private Board board;
    private int maxRow;
    private int maxCol;

    public BoardPanel(Board board){
        this.board = board;
        this.maxRow = board.getMaxRow();
        this.maxCol = board.getMaxCol();

        this.setLayout(new GridLayout(maxRow, maxCol));
    }
}