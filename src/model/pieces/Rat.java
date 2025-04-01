package model.pieces;

import model.Player;

/**
 * This class inherits from the Piece class and is the weakest Piece in the game
 */
public class Rat extends Piece {
    /**
     * Constructs a Rat instance with a strength of 1
     * @param owner Player
     */
    public Rat(Player owner){
        super("Rat", 1, owner);
    }
}