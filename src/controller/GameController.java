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

    public GameController(GameState model, GameView view) {
        this.model = model;
        this.view = view;

        this.view.updateInfo(model.getCurrentPlayer().getName());
        this.board = model.getBoard();
        this.maxRow = board.getMaxRow();
        this.maxCol = board.getMaxCol();

        for (int row = 0; row < maxRow; row++) {
            for (int col = 0; col < maxCol; col++) {
                Tile tile = this.board.getTile(row, col);
                ActionListener al = new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        TileButton clickedTile = (TileButton) e.getSource();
                        handleTileClick(clickedTile.getTile());
                    }
                };

                this.view.displayTile(tile, row, col, al);
            }
        }

        this.view.setFrameSize();
    }

    private void handleTileClick(Tile clickedTile) {
        if (selectedTile == null) {
            if (clickedTile.getCurrentPiece() != null
                    && clickedTile.getCurrentPiece().getOwner() == model.getCurrentPlayer()) {
                this.selectedTile = clickedTile;
                // highlight tile
            }
        } else {
            checkMove(selectedTile, clickedTile);
            // clear highlight
            this.selectedTile = null;
        }
    }

    private void checkMove(Tile from, Tile to) { // I'll try to further simplify this pa
        Piece piece = from.getCurrentPiece();
        if (piece != null && piece.getOwner() == model.getCurrentPlayer()) { // If current player is playing
            if (isValidMove(from, to)) { // If destination of the piece is only one tile difference
                if (to.getCurrentPiece() != null && piece.canCapture(to.getCurrentPiece())) { 
                    to.setCurrentPiece(null); // Remove current piece from tile
                    movePiece(from, to, piece);
                }

                else if (to.getCurrentPiece() == null && !(to instanceof Lake) && !(to instanceof HomeBase) && !(to instanceof Trap)){
                    movePiece(from, to, piece);
                }

                // Special handling for Lion/Tiger crossing a lake
                else if (to instanceof Lake && (piece instanceof Lion || piece instanceof Tiger || piece instanceof Rat)) {
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

                    movePiece(from, to, piece);
                }

                // check victory condition
                else if (to instanceof HomeBase && ((HomeBase) to).getOwner() != piece.getOwner()) {
                    movePiece(from, to, piece);
                    view.showWinner(piece.getOwner().getName());
                }

                // if trap
                else if (to instanceof Trap && ((Trap) to).getOwner() != piece.getOwner()) {
                    piece.setTrapped(true);
                    movePiece(from, to, piece);
                }

                // if previous tile is trap and next tile is not
                else if (from instanceof Trap && !(to instanceof Trap)) {
                    piece.setTrapped(false);
                    movePiece(from, to, piece);
                } else {
                    view.displayError();
                }
            }
        }
    }

    private void movePiece(Tile from, Tile to, Piece piece){
        to.setCurrentPiece(piece);
        from.setCurrentPiece(null);
        this.model.switchTurn();
        this.updateView(to, to.getRow(), to.getCol());
    }

    private boolean isValidMove(Tile from, Tile to) { // I'll try to clean this up
        int rowDiff = Math.abs(from.getRow() - to.getRow());
        int colDiff = Math.abs(from.getCol() - to.getCol());

        if ((rowDiff == 1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1)) { // Ensure that piece is moving one tile at a time
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
                                return false; // Blocked by piece in Lake
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

    private boolean isCaptureAllowed(Piece attacker, Piece target) {
        if (target == null) {
            return true;
        }

        return attacker.canCapture(target);
    }

    private void updateView(Tile clickedTile, int row, int col) {
        this.view.updateBoard(clickedTile, row, col);
        this.view.updateBoard(selectedTile, selectedTile.getRow(), selectedTile.getCol());
        this.view.updateInfo(model.getCurrentPlayer().getName());
        this.view.removeError();
    }
