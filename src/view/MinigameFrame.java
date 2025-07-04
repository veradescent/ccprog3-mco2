package view;

import model.board.Animal;
import model.GameState;
import view.GameView;
import controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * This class serves as the view responsible for selecting the player who will play first in the main game
 */
public class MinigameFrame extends JFrame {
    private ArrayList<Animal> animals;
    private Animal winningAnimal;
    private int p1Choice = -1, p2Choice = -1;
    private JLabel infoLabel;
    private JButton[] buttons;
    private int firstPlayer;

    /**
     * Constructs an initial frame for the mini-game in selecting the first player
     */
    public MinigameFrame() {
        animals = new ArrayList<>(Arrays.asList(
            new Animal("Lion", 7),
            new Animal("Tiger", 6),
            new Animal("Elephant", 8),
            new Animal("Leopard", 5),
            new Animal("Cat", 2),
            new Animal("Dog", 3),
            new Animal("Rat", 1),
            new Animal("Wolf", 4)
        ));
        Collections.shuffle(animals);
        winningAnimal = animals.get(new Random().nextInt(animals.size()));

        setTitle("Turn Picking Minigame");
        setSize(400, 300);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        infoLabel = new JLabel("Player 1, pick a tile (1-8)", SwingConstants.CENTER);
        add(infoLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 4));
        buttons = new JButton[8];

        for (int i = 0; i < 8; i++) {
            int index = i;
            buttons[i] = new JButton("" + (i + 1));
            buttons[i].addActionListener(e -> handleTileSelection(index));
            buttonPanel.add(buttons[i]);
        }
        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    /**
     * This method handles the display of the Pieces once selected by Player
     * @param index Index of selected button
     */
    private void handleTileSelection(int index) {
        String path = "resources\\images\\" + animals.get(index).getName().toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon(path);
        Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        buttons[index].setIcon(new ImageIcon(scaledImage));
        if (p1Choice == -1) {
            p1Choice = index;
            infoLabel.setText("Player 2, pick a tile (1-8)");
        } else if (p2Choice == -1) {
            p2Choice = index;
            determineWinner();
        }
    }

    /**
     * This method compares the choices of the Player and decides who will play first
     */
    private void determineWinner() {
        Animal p1Animal = animals.get(p1Choice);
        Animal p2Animal = animals.get(p2Choice);

        if (p1Animal.getStrength() > p2Animal.getStrength()) {
            firstPlayer = 0;
            JOptionPane.showMessageDialog(this, "Player 1 goes first! " + p1Animal.getName() + " is stronger than " + p2Animal.getName() + "!");
        } else if (p2Animal.getStrength() > p1Animal.getStrength()) {
            firstPlayer = 1;
            JOptionPane.showMessageDialog(this, "Player 2 goes first! " + p2Animal.getName() + " is stronger than " + p1Animal.getName() + "!");
        } else {
            firstPlayer = new Random().nextBoolean() ? 0 : 1;
            JOptionPane.showMessageDialog(this, "It's a tie! Randomly selecting Player " + firstPlayer + " to go first.");
        }

        startMainGame();
    }

    /**
     * This method starts the main game once the first player has been selected
     */
    private void startMainGame() {
        dispose();
        GameState model = new GameState(firstPlayer);
        GameView view = new GameView(model.getBoard());
        GameController gameController = new GameController(model, view);
    }
}
