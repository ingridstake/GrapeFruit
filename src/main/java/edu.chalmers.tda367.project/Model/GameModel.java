package main.java.edu.chalmers.tda367.project.Model;

import main.java.edu.chalmers.tda367.project.Model.GameBoard.GameBoard;

public class GameModel {
    public GameBoard gameBoard;

    public GameModel() {
        gameBoard = new GameBoard(1);
    }
}
