package edu.chalmers.grapefruit.View;

import edu.chalmers.grapefruit.Model.GameBoard.CurrentPlayer;
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
import java.util.ArrayList;
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
    @FXML AnchorPane tileButtonsPane;
    @FXML Button payToOpenBtn;
    @FXML Button diceToOpenBtn;
    List<ViewEntity> viewEntities;
    FXMLLoader fxmlLoader;
    List<Node> playerCards = new ArrayList<>();
    CurrentPlayer currentPlayer;

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
     * @param payToOpenBtnHandler
     * @throws IOException
     */
    public void populate(List<ViewEntity> viewEntities, NodeClickHandler clickHandler, EventHandler diceHandler, EventHandler payToOpenBtnHandler) throws IOException {
        this.viewEntities = viewEntities;
        NodeView.setClickHandler(clickHandler);
        diceBtn.setOnAction(diceHandler);
        payToOpenBtn.setOnAction(payToOpenBtnHandler);

        redrawChildren();
    }

    /**
     * Creates a PlayerCardView for each playerCardResource, and makes the gameBoard keep track of which one represents the current player.
     * @param playerCardResources is the list from which the cards are created.
     * @param currentPlayer is the current player of the game.
     * @throws IOException if a node cannot be loaded from a playerCardResource.
     */
    public void addPlayerCards(List<PlayerCardResource> playerCardResources, CurrentPlayer currentPlayer) {

        this.currentPlayer = currentPlayer;

        int i = 0;
        for (PlayerCardResource playerCardResource : playerCardResources) {
            try {
                Node card  = PlayerCardView.createPlayerCardNode(playerCardResource);
                playerCards.add(card);
                background.getChildren().add(card);
                AnchorPane.setBottomAnchor(card, 0.0);
                AnchorPane.setRightAnchor(card, 10.0 + i * 155);

            } catch (Exception e) {
                e.printStackTrace();
            }

            i++;
        }
    }

    public FXMLLoader getFXMLLoader() {
        return fxmlLoader;
    }

    //TODO: creda den smarta fan som klurade ut hur man gör detta: https://stackoverflow.com/questions/40754454/get-controller-instance-from-node
    /**
     * Finds and returns the fx:controller of a Node.
     * @param node is the node of interest.
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

        for (Node node : playerCards) {
            updatePlayerCard(node);
            background.getChildren().add(node);
        }

        background.getChildren().add(diceBtn);
        background.getChildren().add(tileButtonsPane);
    }

    /**
     * Updates the parameter node so that it represents its player's current state.
     * @param node should have a fx:controller that is an instance of PlayerCardView.
     */
    private void updatePlayerCard(Node node) {
            try {
                PlayerCardView playerCardView = PlayerCardView.getPlayerCardController(node);
                playerCardView.update();
                if (playerCardView.representsWinner()){
                    AnchorPane.setBottomAnchor(node, 400.0);
                    AnchorPane.setRightAnchor(node, 615.0);
                } else if (playerCardView.representsCurrentPlayer(currentPlayer)) {
                    AnchorPane.setBottomAnchor(node, 15.0);
                } else {
                    AnchorPane.setBottomAnchor(node, 0.0);
                }
            } catch (Exception e) {
                e.printStackTrace();
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