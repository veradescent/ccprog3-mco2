package model.pieces;

import model.Player;

public abstract class Piece {
    protected String name;
    protected int strength;
    protected Player owner;
    protected boolean trapped;

    public Piece(String name, int strength, Player owner){
        this.name = name;
        this.strength = strength;
        this.owner = owner;
        this.trapped = false;
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

    public boolean canCapture(Piece target){
        boolean flag = this.strength >= target.getStrength();
        if (target.getOwner() == this.owner){
            flag = false;
        } 

        if (this instanceof Rat && target instanceof Elephant){ // exception
            flag = true;
        }

        return flag;
    }

    public void setTrapped(boolean trapped){
        this.trapped = trapped;

        if (trapped == true){
            this.strength = 0;
        } else if (trapped == false){ 
            if (this instanceof Elephant){
                this.strength = 8;
            } else if (this instanceof Lion){
                this.strength = 7;
            } else if (this instanceof Tiger){
                this.strength = 6;
            } else if (this instanceof Leopard){
                this.strength = 5;
            } else if (this instanceof Wolf){
                this.strength = 4;
            } else if (this instanceof Dog){
                this.strength = 3;
            } else if (this instanceof Cat){
                this.strength = 2;
            } else if (this instanceof Rat){
                this.strength = 1;
            }
        }
    }
}
