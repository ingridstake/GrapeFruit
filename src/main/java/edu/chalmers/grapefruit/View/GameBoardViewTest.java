package edu.chalmers.grapefruit.View;

import edu.chalmers.grapefruit.Utils.NodeClickHandler;
import edu.chalmers.grapefruit.Utils.IPositionable;

import edu.chalmers.grapefruit.Utils.Observer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * @author ingrid.stake
 * @author tove.nilsson
 */

public class GameBoardViewTest implements Observer {

    @FXML AnchorPane background;
    @FXML Button diceBtn;
    List<IPositionable> positionables;

    FXMLLoader fxmlLoader;

    public GameBoardViewTest (){
        fxmlLoader = new FXMLLoader(GameBoardViewTest.class.getResource("background.fxml"));
        /*
        fxmlLoader.setRoot(this);
        //fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

         */
    }

    public FXMLLoader getFXMLLoader() {
        return fxmlLoader;
    }

    /**
     * Populates the GameBoardView with all objects in the positionableList.
     * @param positionableList is the list of Positionable objects that is displayed.
     * @param clickHandler is the event handler for the Nodes.
     * @throws IOException
     */
    public void populate (List<IPositionable> positionableList, NodeClickHandler clickHandler, EventHandler diceHandler) throws IOException {

        this.positionables = positionableList;
        NodeView.setClickHandler(clickHandler);
        diceBtn.setOnAction(diceHandler);

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
                nodeView.initialize(x, y);
            }
            child.relocate(x, y);

            i++;
            if (i >= positionables.size()) {
                break;
            }
        }
        background.getChildren().add(diceBtn);
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