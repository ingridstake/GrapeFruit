package main.java.edu.chalmers.tda367.project;

import main.java.edu.chalmers.tda367.project.Controller.GameController;
import main.java.edu.chalmers.tda367.project.Model.GameModel;

public class Main {

    public static void main(String[] args) {
	// write your code here
        GameModel gameModel = new GameModel();
        GameController gameController = new GameController(gameModel);

        gameController.run();
    }
}
