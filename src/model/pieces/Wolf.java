package model.pieces;

import model.Player;

/**
 * This class inherits from the Piece class and is the fourth to the weakest Piece in the game
 */
public class Wolf extends Piece {
    /**
     * Constructs a Wolf instance with a strength of 4
     * @param owner Player
     */
    public Wolf(Player owner){
        super("Wolf", 4, owner);
    }
}