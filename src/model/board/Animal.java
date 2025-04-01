package model.board;

/**
 * This class serves as a simple representation of the Pieces for the Mini-Game (selection of first player)
 */
public class Animal {
    private String name;
    private int strength;

    /**
     * Constructs an initial animal with a given name and strength
     * @param name Name of Animal
     * @param strength Strength of Animal
     */
    public Animal(String name, int strength) {
        this.name = name;
        this.strength = strength;
    }

    /**
     * This is the getter method for the name
     * @return Name of Animal
     */
    public String getName() {
        return name;
    }

    /**
     * This is the getter method for the strength
     * @return Strength of Animal
     */
    public int getStrength() {
        return strength;
    }
}
