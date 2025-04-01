package model.pieces;

import model.Player;

/**
 * This class serves as the superclass model of all Pieces in the game
 */
public abstract class Piece {
    protected String name;
    protected int strength;
    protected Player owner;
    protected boolean trapped;

    /**
     * Constructs a Piece instance with a given name, strength, and owner
     * @param name Name of Piece
     * @param strength Strength of Piece
     * @param owner Player owning Piece
     */
    public Piece(String name, int strength, Player owner){
        this.name = name;
        this.strength = strength;
        this.owner = owner;
        this.trapped = false;
    }

    /**
     * This is the getter method for the name
     * @return Name of Piece
     */
    public String getName(){
        return this.name;
    }

    /**
     * This is the getter method for the strength
     * @return Strength of Piece
     */
    public int getStrength(){
        return this.strength;
    }

    /**
     * This is the getter method for the owner
     * @return Player owning the Piece
     */
    public Player getOwner(){
        return this.owner;
    }

    /**
     * This method decides if the Piece is able to capture the indicated target
     * @param target Piece wanting to be captured
     * @return true if target can be captured, otherwise false
     */
    public boolean canCapture(Piece target){
        boolean flag = this.strength >= target.getStrength();
        if (target.getOwner() == this.owner){
            flag = false;
        } 

        if (this instanceof Rat && target instanceof Elephant){ // Exception
            flag = true;
        }

        return flag;
    }

    /**
     * This is a setter method for when the Piece is trapped
     * @param trapped true if trapped, false otherwise
     */
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