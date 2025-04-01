package model.board;

import model.Player;

/**
 * This class serves as the Trap model of the game which inherits from the Tile class
 */
public class Trap extends Tile {
    private Player owner;

    /**
     * Constructs a Trap with an indicated row, column, and owner
     * @param row Row of Trap tile
     * @param col Column of Trap tile
     * @param owner Player owning the Trap
     */
    public Trap(int row, int col, Player owner){
        super(row, col);
        this.owner = owner;
    }

    /**
     * This is a getter method for the owner
     * @return Player owning the Trap
     */
    public Player getOwner(){
        return this.owner;
    }
}