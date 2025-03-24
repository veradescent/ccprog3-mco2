package model.pieces;

import model.Player;

public class Cat extends Piece {
    public Cat(int row, int col, Player owner){
        super("Cat", 2, row, col, owner);
    }
}