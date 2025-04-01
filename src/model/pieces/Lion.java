package model.pieces;

import model.Player;

public class Lion extends Piece implements Swimmer{
    public Lion(int row, int col, Player owner){
        super("Lion", 7, row, col, owner);
    }
    
    public boolean crossLake(){
        return true;
    }

    public boolean swim(){
        return false;
    }

}