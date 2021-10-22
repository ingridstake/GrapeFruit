package edu.chalmers.grapefruit.View;


import edu.chalmers.grapefruit.Utils.PlayerCardResource;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;

/**
 * @author ingrid.stake
 */

public class PlayerCardView {

    /**
     * Creates a Node from the playerCardResource.
     * @param playerCardResource is the resource of the Node
     * @return the node that is created.
     * @throws IOException if the playerCardResource cannot be loaded.
     */
    public static Node createPlayerCardNode (PlayerCardResource playerCardResource) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(GameBoardView.class.getResource(playerCardResource.getResourceString()));
        Node card =  fxmlLoader.load();

        PlayerCardView playerCardView = getPlayerCardController(card);
        playerCardView.setPlayerCardResource(playerCardResource);

        return card;
    }

    /**
     * Returns the controller of a PlayerCard node fx:controller.
     * @param node is the node of interest, its fx:Controller should be an instance of PlayerCardView.
     * @return the fx:controller of the node as a PlayerCardView.
     * @throws Exception if the fx:controller of the node cannot be cast to a PlayerCardView.
     */
    public static PlayerCardView getPlayerCardController(Node node) throws Exception {
        Object controllerObject = GameBoardView.getController(node);

        PlayerCardView playerCardView = controllerObject instanceof PlayerCardView ? (PlayerCardView) controllerObject : null;

        if (playerCardView == null) {
            throw new Exception("The argument node's fx:controller is not an instance of PlayerCardView");
        }

        return playerCardView;
    }

    private PlayerCardResource playerCardResource;

    @FXML private Label moneyBalanceLabel1;
    @FXML private ImageView cowImage1;
    @FXML private ImageView cowbellImage1;


    //TODO: detta är inte superelegant lösning, lite för invecklat för en vy kanske?
    /**
     * returns true if the current player matches with the player that the card is representing.
     * @param currentPlayerId is the current player that is compared with.
     * @return true if there is a match.
     */
    public boolean representsCurrentPlayer(int currentPlayerId) {
        return playerCardResource.hasSamePlayer(currentPlayerId);
    }

    public boolean representsWinner() {
        return playerCardResource.isWinner();
    }

    private void setPlayerCardResource(PlayerCardResource playerCardResource) {
        this.playerCardResource = playerCardResource;
        update();
    }

    /**
     * Updates the PlayerCard to show the accurate assets of the player.
     */
    public void update() {
        moneyBalanceLabel1.setText(String.valueOf(playerCardResource.getMoneyBalance()));
        if (playerCardResource.hasCow()) {
            cowImage1.toFront();
            cowImage1.setVisible(true);
        } else if (playerCardResource.hasVisa()) {
            cowbellImage1.toFront();
        } else {
            cowImage1.toBack();
            cowbellImage1.toBack();
        }
    }
}