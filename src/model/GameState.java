package model;

import java.util.ArrayList;
import model.board.*;

public class GameState {
    private Board board;
    private ArrayList<Player> players;
    private Player currentPlayer;
    private boolean gameOver;

    public GameState(){
        initializeGame();
    }

    public Board getBoard(){
        return this.board;
    }

    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }

    public ArrayList<Player> getPlayers(){
        return this.players;
    }

    public boolean isGameOver(){
        return this.gameOver;
    }

    public void resetGame(){
        initializeGame();
    }

    private void initializeGame(){
        this.players = new ArrayList<Player>();
        this.players.add(new Player("Player 1"));
        this.players.add(new Player("Player 2"));
        this.board = new Board(players);
        this.currentPlayer = this.players.get(0);
    }
}
