package model;

/**
 * This class serves as the Player model of the game
 */
public class Player {
    private String name;

    /**
     * Constructs a Player instance with a specified name
     * @param name Name of Player
     */
    public Player(String name){
        this.name = name;
    }

    /**
     * This is a getter method for the name
     * @return Name of Player
     */
    public String getName(){
        return this.name;
    }
}