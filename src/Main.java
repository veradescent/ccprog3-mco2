import view.MinigameFrame;

import javax.swing.SwingUtilities;

/**
 * Main class of the game
 */
public class Main {
    /**
     * Main method to be called
     * @param args String[]
     */
        public static void main(String[] args) {
            SwingUtilities.invokeLater(MinigameFrame::new);
        }
    }
