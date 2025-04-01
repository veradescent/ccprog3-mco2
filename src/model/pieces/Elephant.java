package model.pieces;

import model.Player;

/**
 * This class inherits from the Piece class and is the strongest Piece in the game
 */
public class Elephant extends Piece {
    /**
     * Constructs an Elephant instance with a strength of 8
     * @param owner Player
     */
    public Elephant(Player owner){
        super("Elephant", 8, owner);
    }
}