package model.board;

import model.Player;

/**
 * This class serves as the HomeBase model of the game which inherits from the Tile class
 */
public class HomeBase extends Tile {
    private Player owner;

    /**
     * Constructs an initial HomeBase with an indicated row, column, and owner
     * @param row Row of HomeBase Tile
     * @param col Column of HomeBase Tile
     * @param owner Player owning the HomeBase
     */
    public HomeBase(int row, int col, Player owner){
        super(row, col);
        this.owner = owner;
    }

    /**
     * This is a getter method for the owner
     * @return Player owning the HomeBase
     */
    public Player getOwner(){
        return this.owner;
    }
}