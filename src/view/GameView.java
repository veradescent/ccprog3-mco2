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
    private JTextArea playerTurnLbl;
    private JTextArea errorLbl;

    public GameView(Board board){
        this.mainFrame = new JFrame("Jungle King");
        this.tileGrid = new TileButton[board.getMaxRow()][board.getMaxCol()];
     
        this.mainFrame.setSize(800, 600);
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLayout(new BorderLayout());

        this.boardPanel = new BoardPanel(board);
        this.infoPanel = new InfoPanel();
        this.playerTurnLbl = new JTextArea();
        this.errorLbl = new JTextArea();
       
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

     public void displayError(){
        this.errorLbl.setText("Invalid move. Try again.");
        this.infoPanel.add(errorLbl, FlowLayout.CENTER);
        this.errorLbl.setVisible(true);
    }

    public void removeError(){
        this.errorLbl.setVisible(false);
    }

    public void setFrameSize(){
        this.mainFrame.pack();
    }

    public void showWinner(String winner){
        JOptionPane.showMessageDialog(this.mainFrame, winner + " wins the game!");
    }
}
