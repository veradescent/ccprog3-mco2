package model.pieces;

import model.Player;

/**
 * This class inherits from the Piece class and is the second to the strongest Piece in the game
 */
public class Lion extends Piece implements Swimmer {
    /**
     * Constructs a Lion instance with a strength of 7
     * @param owner Player
     */
    public Lion(Player owner){
        super("Lion", 7, owner);
    }

    /**
     * Implements the ability to be able to cross a Lake
     */
    public boolean crossLake(){
        return true;
    }
}