package model.board;

import model.pieces.Piece;

/**
 * This class serves as the Tile model contained by the Board
 */
public class Tile {
    private int row;
    private int col;
    private Piece currentPiece;

    /**
     * Constructs an initial Tile by a given row and column
     * @param row
     * @param col
     */
    public Tile(int row, int col) {
        this.row = row;
        this.col = col;
        this.currentPiece = null;
    }

    /**
     * This is a getter method of the row
     * @return Row of tile
     */
    public int getRow(){
        return this.row;
    }

    /**
     * This is a getter method of the column
     * @return Column of tile
     */
    public int getCol(){
        return this.col;
    }

    /**
     * This is a getter method for the current piece
     * @return Current Piece of Tile
     */
    public Piece getCurrentPiece(){
        return this.currentPiece;
    }

    /**
     * This is a setter method for the current piece
     * @param piece Current Piece of Tile
     */
    public void setCurrentPiece(Piece piece){
        this.currentPiece = piece;
    }

    /**
     * This method indicates if the Tile has a Piece or not
     * @return true if Tile has Piece, otherwise false
     */
    public boolean hasPiece(){
        boolean flag = false;
        if (this.currentPiece != null){
            flag = true;
        }

        return flag;
    }
}