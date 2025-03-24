package model.pieces;

import model.Player;

public class Dog extends Piece {
    public Dog(int row, int col, Player owner){
        super("Dog", 3, row, col, owner);
    }
}