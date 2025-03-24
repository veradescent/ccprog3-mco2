package model.pieces;

import model.Player;

public class Elephant extends Piece {
    public Elephant(int row, int col, Player owner){
        super("Elephant", 8, row, col, owner);
    }
}