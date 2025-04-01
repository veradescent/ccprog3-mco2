package model.pieces;

import model.Player;

/**
 * This class inherits from the Piece class and is the third to the weakest Piece in the game
 */
public class Dog extends Piece {
    /**
     * Constructs a Dog instance with a strength of 3
     * @param owner Player
     */
    public Dog(Player owner){
        super("Dog", 3, owner);
    }
}