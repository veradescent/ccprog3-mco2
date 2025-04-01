package controller;

import model.GameState;
import view.GameView;
import view.TileButton;
import model.board.*;
import model.pieces.*;
import java.awt.event.*;

public class GameController {
    private GameState model;
    private GameView view;
    private int maxRow;
    private int maxCol;
    private Board board;
    private Tile selectedTile;

    public GameController(GameState model, GameView view){
        this.model = model;
        this.view = view;

        this.view.updateInfo(model.getCurrentPlayer().getName());
        this.board = model.getBoard();
        this.maxRow = board.getMaxRow();
        this.maxCol = board.getMaxCol();

        for (int row = 0; row < maxRow; row++){
            for (int col = 0; col < maxCol; col++){
                Tile tile = this.board.getTile(row, col);
                ActionListener al = new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        TileButton clickedTile = (TileButton) e.getSource();
                        handleTileClick(clickedTile.getTile());
                    }
                };

                this.view.displayTile(tile, row, col, al);
            }
        }

        this.view.setFrameSize();
    }

    private void handleTileClick(Tile clickedTile){
        if (selectedTile == null){
            if (clickedTile.getCurrentPiece() != null && clickedTile.getCurrentPiece().getOwner() == model.getCurrentPlayer()){
                this.selectedTile = clickedTile;
                // highlight tile
            }
        } else {
            movePiece(selectedTile, clickedTile);
            // clear highlight
            this.selectedTile = null;
        }
    }

    private void movePiece(Tile from, Tile to){
        Piece piece = from.getCurrentPiece();
        if (piece != null && piece.getOwner() == model.getCurrentPlayer()){
            if (isValidMove(from, to)){
                if (to.getCurrentPiece() != null && piece.canCapture(to.getCurrentPiece())){
                    to.setCurrentPiece(null); // Capture opponent piece
                }

                to.setCurrentPiece(piece);
                from.setCurrentPiece(null);

                // check victory condition

                model.switchTurn();
            }
        }

        updateView(to, to.getRow(), to.getCol());
    }

    private boolean isValidMove(Tile from, Tile to){
        int rowDiff = Math.abs(from.getRow() - to.getRow());
        int colDiff = Math.abs(from.getCol() - to.getCol());

        if ((rowDiff == 1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1)){
            return isCaptureAllowed(from.getCurrentPiece(), to.getCurrentPiece());
        }

        return false;
    }

    private boolean isCaptureAllowed(Piece attacker, Piece target){
        if (target == null){
            return true;
        }

        return attacker.canCapture(target);
    }

    private void updateView(Tile clickedTile, int row, int col){
       view.updateBoard(clickedTile, row, col);
       view.updateBoard(selectedTile, selectedTile.getRow(), selectedTile.getCol());
    }
}
