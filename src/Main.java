import model.GameState;
import view.GameView;
import view.MinigameFrame;

import javax.swing.SwingUtilities;

import controller.GameController;

public class Main {

        public static void main(String[] args) {
            SwingUtilities.invokeLater(MinigameFrame::new);
        }
    
        private static void startGame(int firstPlayer) {
            GameState model = new GameState();
            GameView view = new GameView(model.getBoard());
            GameController gameController = new GameController(model, view, firstPlayer);
        }
    }
