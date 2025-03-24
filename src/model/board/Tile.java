package model.board;

import model.pieces.Piece;

public class Tile {
    private int row;
    private int col;
    private Piece currentPiece;

    public Tile(int row, int col) {
        this.row = row;
        this.col = col;
        this.currentPiece = null;
    }

    public Piece getCurrentPiece(){
        return this.currentPiece;
    }

    public void setCurrentPiece(Piece piece){
        this.currentPiece = piece;
    }

    public boolean hasPiece(){
        boolean flag = false;
        if (this.currentPiece != null){
            flag = true;
        }

        return flag;
    }
}