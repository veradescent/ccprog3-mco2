package model;

import java.util.ArrayList;
import model.pieces.*;

public class Player {
    private String name;
    private ArrayList<Piece> pieces;
    private boolean turn;

    public Player(String name){
        this.name = name;
        this.pieces = null;
        this.turn = false;
    }

    public String getName(){
        return this.name;
    }

    public void setPieces(ArrayList<Piece> pieces){
        this.pieces = pieces;
    }

    public boolean isTurn(){
        return this.turn;
    }
}