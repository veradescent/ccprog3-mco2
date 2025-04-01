package model.pieces;

import model.Player;

/**
 * This class inherits from the Piece class and is the third to the strongest Piece in the game
 */
public class Tiger extends Piece implements Swimmer {
    /**
     * Constructs a Tiger instance with a strength of 6
     * @param owner Player
     */
    public Tiger(Player owner){
        super("Tiger", 6, owner);
    }

    /**
     * Implements the ability to be able to cross a Lake
     */
    public boolean crossLake(){
        return true;
    }
}