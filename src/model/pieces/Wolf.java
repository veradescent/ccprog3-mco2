package model.pieces;

import model.Player;

public class Wolf extends Piece {
    public Wolf(int row, int col, Player owner){
        super("Wolf", 4, row, col, owner);
    }
}