package model;

import java.util.ArrayList;
import model.pieces.*;

public class Player {
    private String name;
    private ArrayList<Piece> pieces;

    public Player(String name){
        this.name = name;
        this.pieces = null;
    }

    public String getName(){
        return this.name;
    }

    public void setPieces(ArrayList<Piece> pieces){
        this.pieces = pieces;
    }

    public ArrayList<Piece> getPieces(){
        return this.pieces;
    }
}
