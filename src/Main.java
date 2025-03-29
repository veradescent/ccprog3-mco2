import controller.GameController;
import view.GameView;
import model.GameState;

public class Main {
    public static void main(String[] args){
        GameState model = new GameState();
        GameFrame view = new GameView(model.getBoard());

        GameController controller = new GameController(model, view);
    }
}
