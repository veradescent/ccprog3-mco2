import model.GameState;
import view.GameView;
import controller.GameController;

public class Main {
    public static void main(String[] args){
        GameState model = new GameState();
        GameView view = new GameView(model.getBoard());

        GameController gameController = new GameController(model, view);
    }
}
