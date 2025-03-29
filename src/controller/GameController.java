package controller;

import model.GameState;
import view.GameView;
import model.board.*;
import java.awt.event.*;

public class GameController {
    private GameState model;
    private GameView view;

    public GameController(GameState model, GameView view){
        this.model = model;
        this.view = view;

        this.view.updateInfo(model.getCurrentPlayer().getName());

        int maxRow = model.getBoard().getMaxRow();
        int maxCol = model.getBoard().getMaxCol();

        for (int row = 0; row < maxRow; row++){
            for (int col = 0; col < maxCol; col++){
                Tile tile = model.getBoard().getTile(row, col);
                ActionListener al = new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        // add action when tile is pressed
                        /* check currentPlayer, if there is a piece and owner == currentPlayer, 
                        highlight tile and allowMove (handled by GameState) */
                    }
                };

                this.view.displayTile(tile, row, col, al);
            }
        }

        this.view.setFrameSize();
    }
}
