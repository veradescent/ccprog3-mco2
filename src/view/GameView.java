package view;

import model.board.Board;
import model.board.Tile;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameView {
    private JFrame mainFrame;
    private BoardPanel boardPanel;
    private InfoPanel infoPanel;
    private TileButton[][] tileGrid;
    private JLabel playerTurnLbl;

    public GameView(Board board){
        this.mainFrame = new JFrame("Jungle King");
        this.tileGrid = new TileButton[board.getMaxRow()][board.getMaxCol()];
        this.playerTurnLbl = new JLabel();
        this.mainFrame.setSize(800, 600);
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLayout(new BorderLayout());

        this.boardPanel = new BoardPanel(board);
        this.infoPanel = new InfoPanel();
        this.infoPanel.add(playerTurnLbl);
        this.mainFrame.add(boardPanel, BorderLayout.CENTER);
        this.mainFrame.add(infoPanel, BorderLayout.EAST);
        this.mainFrame.setVisible(true);
    }

    public void displayTile(Tile tile, int row, int col, ActionListener al){
        TileButton tileButton = new TileButton(tile);
        tileButton.updateTile(tile);
        tileButton.setPreferredSize(new Dimension(40, 40));
        tileButton.addActionListener(al);
        tileGrid[row][col] = tileButton;
        this.boardPanel.add(tileButton);
    }

    public void updateBoard(Tile tile, int row, int col){
        this.tileGrid[row][col].updateTile(tile);
    }

    public void updateInfo(String playerName){
        this.playerTurnLbl.setText("Current Player: " + playerName);
    }

    public void setFrameSize(){
        this.mainFrame.pack();
    }
}
