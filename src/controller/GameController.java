package controller;

import model.GameState;
import view.GameView;
import view.TileButton;
import model.board.*;
import model.pieces.*;
import java.awt.event.*;
/**
 * This class serves as the main controller of the Game, connecting both model and view
 */
public class GameController {
    private GameState model;
    private GameView view;
    private int maxRow;
    private int maxCol;
    private Board board;
    private Tile selectedTile;

    /**
     * Constructs the initial controller where it sets the action for the clicking of Tiles
     * @param model Game Model
     * @param view Game View
     */
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

    /**
     * This method handles the logic for the clicking of Tiles and moving the Pieces
     * @param clickedTile Tile clicked by Player
     */
    private void handleTileClick(Tile clickedTile) {
        if (selectedTile == null) {
            if (clickedTile.getCurrentPiece() != null
                    && clickedTile.getCurrentPiece().getOwner() == model.getCurrentPlayer()) {
                this.selectedTile = clickedTile;
            }
        } else {
            checkMove(selectedTile, clickedTile);
            this.selectedTile = null;
        }
    }

    /**
     * This method checks the move made by the Player and decides what to do with the move
     * @param from Source tile
     * @param to Destination tile
     */
    private void checkMove(Tile from, Tile to) {
        Piece piece = from.getCurrentPiece();
        if (piece != null && piece.getOwner() == model.getCurrentPlayer()) { // If current player is playing
            if (isValidMove(from, to)) {
                if (to.hasPiece() && piece.canCapture(to.getCurrentPiece())) { 
                    to.setCurrentPiece(null); // Remove current piece from tile
                    movePiece(from, to, piece);
                }

                else if (!(to.hasPiece()) && !(to instanceof Lake) && !(to instanceof HomeBase) && !(to instanceof Trap)){
                    movePiece(from, to, piece);
                }

                else if (to instanceof Lake && (piece instanceof Rat)) {
                    movePiece(from, to, piece);
                }

                // Special handling for Lion/Tiger crossing a lake
                else if (to instanceof Lake && (piece instanceof Lion || piece instanceof Tiger)) {
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
                                } else if (currentTile.hasPiece()){
                                    destination = null;
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
                                } else if (currentTile.hasPiece()){
                                    destination = null;
                                    break;
                                }

                                currentCol += step;
                            }
                        }
                    }

                    if (destination != null) {
                        to = destination;
                        movePiece(from, to, piece);
                    }
                }

                // check victory condition
                else if (to instanceof HomeBase && ((HomeBase) to).getOwner() != piece.getOwner()) {
                    movePiece(from, to, piece);
                    view.showWinner(piece.getOwner().getName());
                }

                // if trap
                else if (to instanceof Trap && ((Trap) to).getOwner() != piece.getOwner()) {
                    movePiece(from, to, piece);
                    piece.setTrapped(true);
                }

                // if previous tile is trap and next tile is not
                else if (from instanceof Trap && !(to instanceof Trap)) {
                    movePiece(from, to, piece);
                    piece.setTrapped(false);
                } else {
                    view.displayError();
                }
            }
        }
    }

    /**
     * This method updates the view once a Piece is moved
     * @param from Source tile
     * @param to Destination tile
     * @param piece Selected piece
     */
    private void movePiece(Tile from, Tile to, Piece piece){
        to.setCurrentPiece(piece);
        from.setCurrentPiece(null);
        this.model.switchTurn();
        this.updateView(to, to.getRow(), to.getCol());
    }

    /**
     * This method checks if the source and destination tile is a valid move
     * @param from Source tile
     * @param to Destination tile
     * @return true if valid, otherwise false
     */
    private boolean isValidMove(Tile from, Tile to) {
        int rowDiff = Math.abs(from.getRow() - to.getRow());
        int colDiff = Math.abs(from.getCol() - to.getCol());

        if ((rowDiff == 1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1)) { // Ensure that piece is moving one tile at a time
            return isCaptureAllowed(from.getCurrentPiece(), to.getCurrentPiece());
        }

        return false;
    }

    /**
     * This method decides if the attacker is allowed to capture the specified target
     * @param attacker Piece selected by Player
     * @param target Opponent Piece to be attacked by Player
     * @return true if capture is allowed, otherwise false
     */
    private boolean isCaptureAllowed(Piece attacker, Piece target) {
        if (target == null) {
            return true;
        }

        return attacker.canCapture(target);
    }

    /**
     * This method updates the display of the Board and the InfoPanel depending on the state of the Board
     * @param clickedTile Tile selected by Player
     * @param row Row of Tile
     * @param col Column of Tile
     */
    private void updateView(Tile clickedTile, int row, int col) {
        this.view.updateBoard(clickedTile, row, col);
        this.view.updateBoard(selectedTile, selectedTile.getRow(), selectedTile.getCol());
        this.view.updateInfo(model.getCurrentPlayer().getName());
        this.view.removeError();
    }
}
