package edu.chalmers.grapefruit.View;

import edu.chalmers.grapefruit.Model.PlayerCardResource;
import edu.chalmers.grapefruit.Model.ViewEntity;
import edu.chalmers.grapefruit.Utils.NodeClickHandler;

import edu.chalmers.grapefruit.Utils.Observer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.List;

/**
 * @author ingrid.stake
 * @author tove.nilsson
 * @author olimanstorm
 * @author elvinafahlgren
 */

public class GameBoardView implements Observer {
    @FXML AnchorPane background;
    @FXML Button diceBtn;
    List<ViewEntity> viewEntities;
    FXMLLoader fxmlLoader;

    /**
     * Creates a FXMLLoader that represents the game board view.
     */
    public GameBoardView(){
        fxmlLoader = new FXMLLoader(GameBoardView.class.getResource("background.fxml"));
    }

    /**
     * Populates the GameBoardView with all objects in the positionableList.
     * @param viewEntities is the list of Positionable objects that is displayed.
     * @param clickHandler is the event handler for the Nodes.
     * @throws IOException
     */
    public void populate (List<ViewEntity> viewEntities, NodeClickHandler clickHandler, EventHandler diceHandler) throws IOException {
        this.viewEntities = viewEntities;
        NodeView.setClickHandler(clickHandler);
        diceBtn.setOnAction(diceHandler);

        redrawChildren();
    }

    public void addPlayerCards(List<PlayerCardResource> playerCardResources) throws IOException {

        int i = 0;
        for (PlayerCardResource playerCardResource : playerCardResources) {
            FXMLLoader fxmlLoader = new FXMLLoader(GameBoardView.class.getResource(playerCardResource.getResourceString()));
            Node card = fxmlLoader.load();
            Object obj = getController(card);

            PlayerCardView playerCardView = obj instanceof PlayerCardView ? (PlayerCardView) obj : null;
            if (playerCardView != null) {
                playerCardView.setPlayerCardResource(playerCardResource);
                playerCardResource.addPlayerObserver(playerCardView);

                background.getChildren().add(card);
                AnchorPane.setBottomAnchor(card, 0.0);
                AnchorPane.setRightAnchor(card, 10.0 + i * 155);
            }
        }
    }

    public FXMLLoader getFXMLLoader() {
        return fxmlLoader;
    }

    //TODO: creda den smarta fan som klurade ut hur man gör detta: https://stackoverflow.com/questions/40754454/get-controller-instance-from-node
    /**
     * Finds and returns the fx:controller of a Node.
     * @param node is the node od interest.
     * @return the fx:controller of the node.
     */
    private static Object getController(Node node) {
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
        for (ViewEntity viewEntity : viewEntities) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(viewEntity.getResourceString()));
            background.getChildren().add(fxmlLoader.load());
        }

        int i = 0;
        for (javafx.scene.Node child : background.getChildren()) {
            int x = viewEntities.get(i).getX();
            int y = viewEntities.get(i).getY();

            Object obj = getController(child);
            NodeView nodeView = obj instanceof NodeView ? (NodeView) obj : null;

            if (nodeView != null) {
                nodeView.initialize(x, y);
            }
            child.relocate(x, y);

            i++;
            if (i >= viewEntities.size()) {
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