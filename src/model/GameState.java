package model;

import java.util.ArrayList;
import model.board.*;

/**
 * This class serves as the model of the overall structure of the game
 */
public class GameState {
    private Board board;
    private ArrayList<Player> players;
    private Player currentPlayer;
    private boolean gameOver;

    /**
     * Constructs the initial game state by accepting the first player
     * @param firstPlayer First player to play
     */
    public GameState(int firstPlayer){
        initializeGame(firstPlayer);
    }

    /**
     * This is a getter method for the Board
     * @return Board of the game
     */
    public Board getBoard(){
        return this.board;
    }

    /**
     * This is a getter method for the current player
     * @return Current player of the game
     */
    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }

    /**
     * This is a getter method for when the game is over
     * @return true if game over, false otherwise
     */
    public boolean isGameOver(){ // not implemented yet
        return this.gameOver;
    }

    /**
     * This is a setter method for when the game is over
     * @param gameOver true if game over, false otherwise
     */
    public void setGameOver(boolean gameOver){ // not implemented yet
        this.gameOver = gameOver;
    }

    /**
     * This method handles the alternating of turns between the two players
     */
    public void switchTurn(){
        this.currentPlayer = (currentPlayer == players.get(0)) ? players.get(1) : players.get(0);
    }

    /**
     * This method creates the initial state of the game
     * @param firstPlayer First player to play
     */
    private void initializeGame(int firstPlayer){
        this.players = new ArrayList<Player>();
        this.players.add(new Player("Player 1"));
        this.players.add(new Player("Player 2"));
        this.board = new Board(players);
        this.currentPlayer = this.players.get(firstPlayer);
        this.gameOver = false;
    }
}