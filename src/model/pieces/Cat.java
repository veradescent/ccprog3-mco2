package model.pieces;

import model.Player;

/**
 * This class inherits from the Piece class and is the second to the weakest Piece in the game
 */
public class Cat extends Piece {
    /**
     * Constructs a Cat instance with a strength of 2
     * @param owner Player
     */
    public Cat(Player owner){
        super("Cat", 2, owner);
    }
}