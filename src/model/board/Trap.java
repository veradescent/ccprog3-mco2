package model.board;

import model.Player;
import model.pieces.*;

public class Trap extends Tile {
    private Player owner;

    public Trap(int row, int col, Player owner){
        super(row, col);
        this.owner = owner;
    }
}