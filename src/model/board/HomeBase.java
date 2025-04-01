package model.board;

import model.Player;
import model.pieces.Piece;

public class HomeBase extends Tile {
    private Player owner;

    public HomeBase(int row, int col, Player owner){
        super(row, col);
        this.owner = owner;
    }

    public Player getOwner(){
        return this.owner;
    }
}
