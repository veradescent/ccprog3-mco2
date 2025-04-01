package model.board;

/**
 * This class serves as the Lake model of the game which inherits from the Tile class
 */
public class Lake extends Tile {
    /**
     * Constructs an initial Lake with an indicated row and column
     * @param row Row of Lake Tile
     * @param col Column of Lake Tile
     */
    public Lake(int row, int col){
        super(row, col);
    }
}