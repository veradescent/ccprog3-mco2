import controller.GameController;
import view.GameFrame;
import model.GameState;

public class Main {
    public static void main(String[] args){
        GameState model = new GameState();
        GameFrame view = new GameFrame();

        GameController controller = new GameController(model, view);
    }
}