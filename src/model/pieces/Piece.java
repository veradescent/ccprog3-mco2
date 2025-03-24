package model.pieces;

import model.Player;

public abstract class Piece {
    protected String name;
    protected int strength;
    protected int row, col;
    protected Player owner;

    public Piece(String name, int strength, int row, int col, Player owner){
        this.name = name;
        this.strength = strength;
        this.row = row;
        this.col = col;
        this.owner = owner;
    }

    public Player getOwner(){
        return this.owner;
    }

    public boolean canCapture(Piece target){
        if (this instanceof Rat && target instanceof Elephant){
            return true;
        }

        return this.strength >= target.strength;
    }

    public void move(int newRow, int newCol){
        this.row = newRow;
        this.col = newCol;
    }
}