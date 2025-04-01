package model.board;

public class Animal {
    private String name;
    private int strength;

    public Animal(String name, int strength) {
        this.name = name;
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }
}
