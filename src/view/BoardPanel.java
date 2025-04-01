package view;

import model.board.Board;

import java.awt.GridLayout;
import javax.swing.*;

/**
 * This class serves as the Board view of the game
 */
public class BoardPanel extends JPanel {
    private Board board;
    private int maxRow;
    private int maxCol;

    /**
     * Constructs an initial view of the Board
     * @param board Board model
     */
    public BoardPanel(Board board){
        this.board = board;
        this.maxRow = board.getMaxRow();
        this.maxCol = board.getMaxCol();

        this.setLayout(new GridLayout(maxRow, maxCol));
    }
}