package controller;

import view.GameFrame;
import model.GameState;

public class GameController {
    private GameState model;
    private GameFrame view;

    public GameController(GameState model, GameFrame view){
        this.model = model;
        this.view = view;
    }
}