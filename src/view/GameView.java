package view;

import model.board.Board;
import model.board.Tile;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * This class serves as the overall view of the game
 */
public class GameView {
    private JFrame mainFrame;
    private BoardPanel boardPanel;
    private InfoPanel infoPanel;
    private JTextArea playerTurnLbl;
    private JTextArea errorLbl;
    private TileButton[][] tileGrid;

    /**
     * Constructs the initial game view by the given board model created by the GameState class
     * @param board Board model
     */
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
        this.mainFrame.add(infoPanel, BorderLayout.SOUTH);
        this.mainFrame.setVisible(true);
    }

    /**
     * This method creates the view for the TileButton to be placed in the BoardPanel
     * @param tile Tile model
     * @param row Row of Tile
     * @param col Column of Tile
     * @param al Action to be implemented by button
     */
    public void displayTile(Tile tile, int row, int col, ActionListener al){
        TileButton tileButton = new TileButton(tile);
        tileButton.updateTile(tile);
        tileButton.setPreferredSize(new Dimension(40, 40));
        tileButton.addActionListener(al);
        tileGrid[row][col] = tileButton;
        this.boardPanel.add(tileButton);
    }

    /**
     * This method updates the view of the overall board once a move has been done
     * @param tile Tile model
     * @param row Row of Tile
     * @param col Column of Tile
     */
    public void updateBoard(Tile tile, int row, int col){
        this.tileGrid[row][col].updateTile(tile);
    }

    /**
     * This method updates the view of the information details of current player
     * @param playerName Current Player playing
     */
    public void updateInfo(String playerName){
        playerTurnLbl.setText(playerName + " is currently making their move");
        this.infoPanel.add(playerTurnLbl);
    }

    /**
     * This method displays the error when the Player makes an invalid move
     */
    public void displayError(){
        this.errorLbl.setText("Invalid move. Try again.");
        this.infoPanel.add(errorLbl, FlowLayout.CENTER);
        this.errorLbl.setVisible(true);
    }

    /**
     * This method removes the error when the Player corrects their move
     */
    public void removeError(){
        this.errorLbl.setVisible(false);
    }

    /**
     * This method sets the frame size of the main frame of the game
     */
    public void setFrameSize(){
        this.mainFrame.pack();
    }

    /**
     * This method displays a message when there has been a declared winner in the game
     * @param winner Player who won the game
     */
    public void showWinner(String winner){
        JOptionPane.showMessageDialog(this.mainFrame, winner + " wins the game!");
    }
}