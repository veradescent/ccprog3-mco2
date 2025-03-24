package model.pieces;

import model.Player;

public class Rat extends Piece {
    public Rat(int row, int col, Player owner){
        super("Rat", 1, row, col, owner);
    }
}