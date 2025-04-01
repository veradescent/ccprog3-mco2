package model.board;

import java.util.ArrayList;

import model.pieces.*;
import model.Player;

/**
 * This class serves as the main Board model of the game
 */
public class Board {
    private int maxRow, maxCol;
    private Tile[][] gameGrid;
    private ArrayList<Player> players;

    /**
     * Constructs an initial 7x9 board with a given array of Players
     * @param players
     */
    public Board(ArrayList<Player> players){
        this.maxRow = 7;
        this.maxCol = 9;
        this.gameGrid = new Tile[maxRow][maxCol];
        this.players = players;

        initializeBoard();
    }

    /**
     * This is the getter method for the max number of rows
     * @return Max rows
     */
    public int getMaxRow(){
        return this.maxRow;
    }

    /**
     * This is the getter method for the max number of columns
     * @return Max columns
     */
    public int getMaxCol(){
        return this.maxCol;
    }

    /**
     * This is the getter method for the tiles contained in the Board
     * @return Tile array
     */
    public Tile[][] getGameGrid(){
        return this.gameGrid;
    }

    /**
     * This is the getter method for the corresponding Tile given by its row and column
     * @param row Row of tile
     * @param col Column of tile
     * @return Tile
     */
    public Tile getTile(int row, int col){
        return this.gameGrid[row][col];
    }

    /**
     * This method initializes the contents of the Board
     */
    private void initializeBoard(){
        for (int row = 0; row < this.getMaxRow(); row++) {
            for (int col = 0; col < this.getMaxCol(); col++) {
                this.gameGrid[row][col] = new Tile(row, col);
            }
        }

        createLake();
        createBase();
        createTrap();
        initializePieces();
    }

    /**
     * This method initializes the positions of the Lake
     */
    private void createLake() {
        gameGrid[1][3] = new Lake(1, 3);
        gameGrid[2][3] = new Lake(2, 3);
        gameGrid[4][3] = new Lake(4, 3);
        gameGrid[5][3] = new Lake(5, 3);

        gameGrid[1][4] = new Lake(1, 4);
        gameGrid[2][4] = new Lake(2, 4);
        gameGrid[4][4] = new Lake(4, 4);
        gameGrid[5][4] = new Lake(5, 4);

        gameGrid[1][5] = new Lake(1, 5);
        gameGrid[2][5] = new Lake(2, 5);
        gameGrid[4][5] = new Lake(4, 5);
        gameGrid[5][5] = new Lake(5, 5);
    }

    /**
     * This method initializes the positions of the HomeBase
     */
    private void createBase() {
        gameGrid[3][0] = new HomeBase(3, 0, this.players.get(0));
        gameGrid[3][8] = new HomeBase(3, 8, this.players.get(1));
    }

    /**
     * This  method initializes the positions of the Trap
     */
    private void createTrap() {
        gameGrid[2][0] = new Trap(2, 0, this.players.get(0));
        gameGrid[4][0] = new Trap(4, 0, this.players.get(0));
        gameGrid[3][1] = new Trap(3, 1, this.players.get(0));
        gameGrid[2][8] = new Trap(2, 8, this.players.get(1));
        gameGrid[4][8] = new Trap(4, 8, this.players.get(1));
        gameGrid[3][7] = new Trap(3, 7, this.players.get(1));
    }

    /**
     * This method initializes the positions of the Pieces
     */
    private void initializePieces() {
        gameGrid[0][0].setCurrentPiece(new Tiger(this.players.get(0)));
        gameGrid[6][8].setCurrentPiece(new Tiger(this.players.get(1)));
    
        gameGrid[6][0].setCurrentPiece(new Lion(this.players.get(0)));
        gameGrid[0][8].setCurrentPiece(new Lion(this.players.get(1)));
    
        gameGrid[1][1].setCurrentPiece(new Cat(this.players.get(0)));
        gameGrid[5][7].setCurrentPiece(new Cat(this.players.get(1)));
    
        gameGrid[5][1].setCurrentPiece(new Dog(this.players.get(0)));
        gameGrid[1][7].setCurrentPiece(new Dog(this.players.get(1)));
    
        gameGrid[0][2].setCurrentPiece(new Elephant(this.players.get(0)));
        gameGrid[6][6].setCurrentPiece(new Elephant(this.players.get(1)));
    
        gameGrid[2][2].setCurrentPiece(new Wolf(this.players.get(0)));
        gameGrid[4][6].setCurrentPiece(new Wolf(this.players.get(1)));
    
        gameGrid[4][2].setCurrentPiece(new Leopard(this.players.get(0)));
        gameGrid[2][6].setCurrentPiece(new Leopard(this.players.get(1)));
    
        gameGrid[6][2].setCurrentPiece(new Rat(this.players.get(0)));
        gameGrid[0][6].setCurrentPiece(new Rat(this.players.get(1)));
    }
}