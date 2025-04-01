package model.pieces;

import model.Player;

public class Tiger extends Piece implements Swimmer{
    public Tiger(int row, int col, Player owner){
        super("Tiger", 6, row, col, owner);
    }

    public boolean crossLake(){
        return true;
    }
}