package view;

import model.GameState;
import model.board.Board;
import model.board.Tile;
import model.pieces.Piece;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameView {
    private JFrame mainFrame;
    private BoardPanel boardPanel;
    private TileButton[][] tileGrid;

    public GameView(Board board){
        this.mainFrame = new JFrame("Jungle King");
        this.tileGrid = new TileButton[board.getMaxRow()][board.getMaxCol()];
        this.mainFrame.setSize(800, 600);
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLayout(new BorderLayout());

        this.boardPanel = new BoardPanel(board);
        this.mainFrame.add(boardPanel, BorderLayout.CENTER);
        this.mainFrame.setVisible(true);
    }

    public void displayTile(Tile tile, int row, int col, ActionListener al){
        TileButton tileButton = new TileButton(tile);
        tileButton.updateTile();
        tileButton.setPreferredSize(new Dimension(40, 40));
        tileButton.addActionListener(al);
        tileGrid[row][col] = tileButton;
        this.boardPanel.add(tileButton);
    }

    public void setFrameSize(){
        this.mainFrame.pack();
    }
}