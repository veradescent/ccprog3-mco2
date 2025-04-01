package model.pieces;

import model.Player;

public class Lion extends Piece implements Swimmer {
    public Lion(Player owner){
        super("Lion", 7, owner);
    }

    public boolean crossLake(){
        return true;
    }
}
