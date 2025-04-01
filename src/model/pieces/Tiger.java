package model.pieces;

import model.Player;

public class Tiger extends Piece implements Swimmer {
    public Tiger(Player owner){
        super("Tiger", 6, owner);
    }

    public boolean crossLake(){
        return true;
    }
}
