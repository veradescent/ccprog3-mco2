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
            // Special handling for Lion/Tiger crossing a lake
            if (piece instanceof Lion || piece instanceof Tiger) {
                Tile destination = null;
    
               // Determine new destination after crossing lake
               if (from.getRow() == to.getRow() || from.getCol() == to.getCol()) {
        
                 if (from.getCol() == to.getCol()) { // Vertical jump
                     int step = (to.getRow() > from.getRow()) ? 1 : -1;
                     int currentRow = from.getRow() + step;
                     while (currentRow >= 0 && currentRow < board.getMaxRow()) {
                         Tile currentTile = board.getTile(currentRow, from.getCol());
                         if (!(currentTile instanceof Lake)) {
                             destination = currentTile;
                             break;
                            }
                            currentRow += step;
                        }
                    } else { // Horizontal jump
                        int step = (to.getCol() > from.getCol()) ? 1 : -1;
                        int currentCol = from.getCol() + step;
                       while (currentCol >= 0 && currentCol < board.getMaxCol()) {
                           Tile currentTile = board.getTile(from.getRow(), currentCol);
                           if (!(currentTile instanceof Lake)) {
                               destination = currentTile;
                               break;
                           }
                           currentCol += step;
                       }
                    }
             }
    
              if (destination != null) {
                    to = destination;
              }
            }

            to.setCurrentPiece(piece);
            from.setCurrentPiece(null);

            // Check victory condition

            model.switchTurn();
            }
        }
            updateView(to, to.getRow(), to.getCol());
    }

    private boolean isValidMove(Tile from, Tile to){
        int rowDiff = Math.abs(from.getRow() - to.getRow());
        int colDiff = Math.abs(from.getCol() - to.getCol());

        if (((rowDiff == 1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1)) && !(to instanceof Lake)){
            return isCaptureAllowed(from.getCurrentPiece(), to.getCurrentPiece());
        }

        //Special Swimmer Condition
        if(((rowDiff == 1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1)) && to instanceof Lake){
            //Special Rat condition
            if(from.getCurrentPiece() instanceof Swimmer && from.getCurrentPiece() instanceof Rat){
                return isCaptureAllowed(from.getCurrentPiece(), to.getCurrentPiece());
            }
            //Special Lion/Tiger Condition Checker (Cross)
            if (from.getCurrentPiece() instanceof Lion || from.getCurrentPiece() instanceof Tiger) {
                Tile destination = null;
                
                    // Vertical cross
                    if (colDiff == 0) {
                        int step = (to.getRow() > from.getRow()) ? 1 : -1;
                        int currentRow = from.getRow() + step;
                        
                        // Checking Lake Tiles
                        while (currentRow >= 0 && currentRow < board.getMaxRow()) {
                            Tile currentTile = board.getTile(currentRow, from.getCol());
                            if (!(currentTile instanceof Lake)) {
                                destination = currentTile;
                                break;
                            }
                            if (currentTile.hasPiece()) {
                                return false; // Blocked by a piece in the lake
                            }
                            currentRow += step;
                        }
                    } 
                    // Horizontal Cross
                    else if (rowDiff == 0) {
                        int step = (to.getCol() > from.getCol()) ? 1 : -1;
                        int currentCol = from.getCol() + step;
                        
                        // Checking Lake Tiles
                        while (currentCol >= 0 && currentCol < board.getMaxCol()) {
                            Tile currentTile = board.getTile(from.getRow(), currentCol);
                            if (!(currentTile instanceof Lake)) {
                                destination = currentTile;
                                break;
                            }
                            if (currentTile.hasPiece()) {
                                return false; // Blocked by peice in Lake
                            }
                            currentCol += step;
                        }
                    }
                
                // If a valid destination was found, update `to` and validate capture
                if (destination != null && destination != from) {
                    return isCaptureAllowed(from.getCurrentPiece(), destination.getCurrentPiece());
                }
            }
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
