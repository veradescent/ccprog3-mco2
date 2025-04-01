package model.pieces;

import model.Player;

public class Rat extends Piece implements Swimmer{
    public Rat(int row, int col, Player owner){
        super("Rat", 1, row, col, owner);
    }

    public boolean crossLake(){
        return false;
    }

    public boolean swim(){
        return true;
    }
}