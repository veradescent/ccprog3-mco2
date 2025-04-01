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

    public String getName(){
        return this.name;
    }

    public int getStrength(){
        return this.strength;
    }

    public Player getOwner(){
        return this.owner;
    }

    public boolean canCapture(Piece target){ // check
        boolean flag = this.strength >= target.getStrength();
        if (target.getOwner() == this.owner){
            flag = false;
        }

        return flag;
    }

    public void move(int newRow, int newCol){ // haven't implemented this yet
        this.row = newRow;
        this.col = newCol;
    }
}
