package model.pieces;

import model.Player;

/**
 * This class inherits from the Piece class and is the fourth to the strongest Piece in the game
 */
public class Leopard extends Piece {
    /**
     * Constructs a Leopard instance with a strength of 5
     * @param owner Player
     */
    public Leopard(Player owner){
        super("Leopard", 5, owner);
    }
}