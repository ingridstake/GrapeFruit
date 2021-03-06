package edu.chalmers.grapefruit.View;

import edu.chalmers.grapefruit.Utils.Listeners.OpenTileOperationsListener;
import edu.chalmers.grapefruit.Utils.PlayerCardResource;
import edu.chalmers.grapefruit.Utils.ViewEntity;
import edu.chalmers.grapefruit.Utils.NodeClickHandler;
import edu.chalmers.grapefruit.Utils.Observer;
import edu.chalmers.grapefruit.Utils.Listeners.NewTurnListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author Ingrid Stake
 * @author Tove Nilsson
 * @author Olivia Månström
 * @author Elvina Fahlgren
 */

public class GameBoardView implements Observer, NewTurnListener, OpenTileOperationsListener {

    @FXML private AnchorPane background;
    @FXML private Button diceBtn;
    @FXML private Button payToOpenBtn;
    @FXML private Button diceToOpenBtn;
    private List<ViewEntity> viewEntities;
    private HashMap<Integer, Node> playerCards = new HashMap<>();
    private int currentPlayerId;
    private boolean showPayToOpenBtn;
    private boolean showDiceToOpenBtn;
    private boolean showDiceBtn;
    private DiceView diceView;

    /**
     * Creates and returns a Gameboard view with an anchorpane.
     * @return a GameBoardView.
     * @throws Exception if the resource is incorrect.
     */
    public static GameBoardView createGameBoardView() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(GameBoardView.class.getResource("background.fxml"));
        AnchorPane background = fxmlLoader.load();

        GameBoardView gameBoardView = getGameBoardViewController(background);
        gameBoardView.background = background;

        return gameBoardView;
    }

    /**
     * Returns the fxController of a node parsed to a GameBoardView.
     * @param node is the node whose controller is of interest.
     * @return the fxController of a node parsed to a GameBoarView.
     * @throws Exception if the fxController of the node cannot be parsed to a GameBoardView.
     */
    public static GameBoardView getGameBoardViewController(Node node) throws Exception {
        Object controller = ViewUtils.getController(node);
        GameBoardView gameBoardView = controller instanceof GameBoardView ? (GameBoardView) controller : null;

        if (gameBoardView == null) {
            throw new Exception("The argument node's fx:controller is not an instance of GameBoardView");
        }
        return gameBoardView;
    }

    /**
     * Sets the GameBoards instance variable viewEntities.
     * Sets the NodeViews clickHandler.
     * Sets the dice buttons event handler.
     * Sets the pay to open buttons event handler.
     * Sets the dice to open buttons event handler.
     * Finally draws the GameBoardView children.
     * @param viewEntities is the list of viewEntityObjects objects that is displayed.
     * @param clickHandler is the event handler for the Nodes.
     * @param payToOpenBtnHandler
     * @param diceToOpenBtnHandler
     * @throws IOException
     */
    public void populate(List<ViewEntity> viewEntities, NodeClickHandler clickHandler, EventHandler diceHandler,
                         EventHandler payToOpenBtnHandler, EventHandler diceToOpenBtnHandler) throws IOException {
        this.viewEntities = viewEntities;
        NodeView.setClickHandler(clickHandler);
        this.diceBtn.setOnAction(diceHandler);
        this.payToOpenBtn.setOnAction(payToOpenBtnHandler);
        this.diceToOpenBtn.setOnAction(diceToOpenBtnHandler);
        showDiceBtn = true;

        try {
            this.diceView = DiceView.createDiceView();
        } catch (Exception e) {
            e.printStackTrace();
        }

        redrawChildren();
    }

    /**
     * Creates a PlayerCardView for each playerCardResource, and makes the gameBoard keep track of which one represents the current player.
     * @param playerCardResources is the list from which the cards are created.
     * @param ids is a list of all player ids.
     * @throws IOException if a node cannot be loaded from a playerCardResource.
     */
    public void addPlayerCards(List<PlayerCardResource> playerCardResources, List<Integer> ids) {

        int i = 0;
        for (PlayerCardResource playerCardResource : playerCardResources) {
            try {
                Node card  = PlayerCardView.createPlayerCardNode(playerCardResource);
                playerCards.put(ids.get(i), card);
                background.getChildren().add(card);
                AnchorPane.setBottomAnchor(card, 0.0);
                AnchorPane.setRightAnchor(card, 10.0 + i * 155);

            } catch (Exception e) {
                e.printStackTrace();
            }
            i++;
        }
    }

    public Node getBackground() {
        return background;
    }

    @Override
    public void update() {
        try {
            redrawChildren();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void newPlayer(int id) {
        this.currentPlayerId = id;
    }

    @Override
    public void newTurn() {
        showDiceBtn = true;
    }

    @Override
    public void updateDiceToOpenTile(boolean canRollDiceToOpenTile) {
        showDiceToOpenBtn = canRollDiceToOpenTile;
    }

    @Override
    public void updatePayToOpenTile(boolean canPayToOpenTile) {
        showPayToOpenBtn = canPayToOpenTile;
    }

    public DiceView getDiceView() {
        return diceView;
    }

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

        drawViewEntities();

        for (Node node : playerCards.values()) {
            updatePlayerCard(node);
            background.getChildren().add(node);
        }

        drawButtons();
        drawDiceView();
    }

    /**
     * Draws all ViewEntities.
     */
    private void drawViewEntities() {
        for (int i = 0; i < background.getChildren().size(); i++) {
            int x = viewEntities.get(i).getX();
            int y = viewEntities.get(i).getY();

            Object obj = ViewUtils.getController(background.getChildren().get(i));
            NodeView nodeView = obj instanceof NodeView ? (NodeView) obj : null;

            if (nodeView != null) {
                nodeView.initialize(x, y);
            }
            background.getChildren().get(i).relocate(x, y);

            if (i >= viewEntities.size()) {
                break;
            }
        }
    }

    /**
     * Renders the buttons that should be visible.
     */
    private void drawButtons(){
        if (showPayToOpenBtn)
            background.getChildren().add(payToOpenBtn);
        if (showDiceToOpenBtn)
            background.getChildren().add(diceToOpenBtn);
        if (showDiceBtn) {
            background.getChildren().add(diceBtn);
            showDiceBtn = false;
        }
    }

    /**
     * Adds the diceView to the background, and then renders it
     */
    private void drawDiceView() {
        background.getChildren().add(diceView.getNode());
        AnchorPane.setRightAnchor(diceView.getNode(), 10.0);
        AnchorPane.setBottomAnchor(diceView.getNode(), 400.0);
    }

    /**
     * Updates the parameter node so that it represents its player's current state.
     * @param node should have a fx:controller that is an instance of PlayerCardView.
     */
    private void updatePlayerCard(Node node) {
            try {
                PlayerCardView playerCardView = PlayerCardView.getPlayerCardController(node);
                playerCardView.update();
                if (playerCardView.representsCurrentPlayer(currentPlayerId)) {
                    AnchorPane.setBottomAnchor(node, 15.0);
                } else {
                    AnchorPane.setBottomAnchor(node, 0.0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}