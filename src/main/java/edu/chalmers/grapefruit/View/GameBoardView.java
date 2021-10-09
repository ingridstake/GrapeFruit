package edu.chalmers.grapefruit.View;

import edu.chalmers.grapefruit.Controller.NodeClickHandler;
import edu.chalmers.grapefruit.Interfaces.IPositionable;

import edu.chalmers.grapefruit.Interfaces.Observer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * @author ingrid.stake
 * @author tove.nilsson
 */

public class GameBoardView implements Observer {

    @FXML AnchorPane background;
    List<IPositionable> positionables;
    NodeClickHandler clickHandler;

    static public GameBoardView makeGameBoardView(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameBoardView.class.getResource("background.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 800);

        GameBoardView view = fxmlLoader.getController();

        stage.setTitle("Den försvunna kossan!");
        stage.setScene(scene);
        stage.show();

        return view;
    }

    /**
     * There need to be a constructor taking no arguments in order for the load of the View's .fxml-file to work
     */
    public GameBoardView () {}

    /**
     * Populates the GameBoardView with all objects in the positionableList.
     * @param positionableList is the list of Positionable objects that is displayed.
     * @param clickHandler is the event handler for the Nodes.
     * @throws IOException
     */
    public void populate (List<IPositionable> positionableList, NodeClickHandler clickHandler) throws IOException {

        this.positionables = positionableList;
        this.clickHandler = clickHandler;

        redrawChildren();
    }

    //TODO: ta bort denna metod om den inte används
    @FXML
    public void initialize() throws IOException {
        //inte säkert att denna kommer till använding, men metoden körs efter att konstruktorn och annan setup körts,
        //
    }

    //TODO: creda den smarta fan som klurade ut hur man gör detta: https://stackoverflow.com/questions/40754454/get-controller-instance-from-node
    /**
     * Finds and returns the fx:controller of a Node.
     * @param node is the node od interest.
     * @return the fx:controller of the node.
     */
    public static Object getController(Node node) {
        Object controller = null;
        do {
            controller = node.getUserData();
            node = node.getParent();
        } while (controller == null && node != null);
        return controller;
    }

    //TODO: Städa upp och snygga till
    /**
     * Repopulates the background with new children to update the view.
     * @throws IOException if any of the fxml objects can't be loaded.
     */
    private void redrawChildren() throws IOException {
        background.getChildren().removeAll(background.getChildren());
        for (IPositionable positionableObject : positionables) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(positionableObject.getResourceString()));
            background.getChildren().add(fxmlLoader.load());
        }
        int i = 0;
        for (javafx.scene.Node child : background.getChildren()) {
            int x = positionables.get(i).getX();
            int y = positionables.get(i).getY();

            NodeView nodeView = (NodeView) getController(child);
            if (nodeView != null) {
                nodeView.initialize(clickHandler, x, y);
            }
            child.relocate(x, y);

            i++;
            if (i >= positionables.size()) {
                break;
            }
        }
    }

    @Override
    public void update() {
        try {
            redrawChildren();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}