package edu.chalmers.grapefruit.View;

import edu.chalmers.grapefruit.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameBoardView {
    AnchorPane anchorPane;

    public static GameBoardView Init(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameBoardView.class.getResource("background.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 800);
        stage.setTitle("Den försvunna kossan!");
        stage.setScene(scene);
        stage.show();
        return fxmlLoader.getController();
    }

    @FXML
    private void initialize() throws IOException {

    }

    private GameBoardView(){

    }

    public GameBoardView ( int nNodes) {
        anchorPane = new AnchorPane();
        List<Circle> circleList = new ArrayList<>();
        for (int i = 0; i<nNodes; i++){
            circleList.add(new Circle());
        }

        anchorPane.getChildren().addAll(circleList);

    }
}
