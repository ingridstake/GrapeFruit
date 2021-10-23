package edu.chalmers.grapefruit.View;

import edu.chalmers.grapefruit.Utils.Listeners.DiceRolledListener;
import edu.chalmers.grapefruit.Utils.PlayerCardResource;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that graphically represent a dice.
 * @author Elvina Fahlgren
 */
public class DiceView implements DiceRolledListener {
    private @FXML ImageView diceValueImage;
    private List<Image> diceImages;
    private Node node;

    /**
     * Creates a DiceView.
     * @return the DiceView that is created.
     * @throws Exception if the fxml file cannot be loaded.
     */
    public static DiceView createDiceView () throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(GameBoardView.class.getResource("dice-view.fxml"));
        Node node =  fxmlLoader.load();
        DiceView diceView = getDiceViewController(node);
        diceView.setNode(node);
        diceView.setDiceImages();

        return diceView;
    }

    /**
     * Retrieves a specific dice view fx:controller and returns it.
     * @param diceNode is the Node that represent
     * @return
     * @throws Exception if argument diceNode's fx:controller is not an instance of DiceView.
     */
    private static DiceView getDiceViewController(Node diceNode) throws Exception {
        Object controllerObject = GameBoardView.getController(diceNode);
        DiceView diceView = controllerObject instanceof DiceView ? (DiceView) controllerObject : null;

        if (diceView == null) {
            throw new Exception("The argument diceNode's fx:controller is not an instance of DiceView");
        }

        return diceView;
    }

    /**
     * Sets the DiceView's instance variable "node".
     * @param node is the node to be set to the instance variable.
     */
    private void setNode(Node node) {
        this.node = node;
    }

    /**
     * Initializes the diceimages list, and adds the 6 different dice imaages to it.
     */
    private void setDiceImages() throws IllegalArgumentException {
        diceImages = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            try {
                diceImages.add(new Image("edu/chalmers/grapefruit/View/images/dice" + i + ".png"));
            }catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("File dice" + i + ".png could not be found!");
            }
        }
    }

    /**
     * Updates to correct dice image, that matches the dice value.
     * @param diceValue is the current value of the dice.
     */
    private void updateDiceValueImage(int diceValue) {
        diceValueImage.setImage(diceImages.get(diceValue - 1));
        diceValueImage.setVisible(true);

    }

    public Node getNode() {
        return node;
    }

    @Override
    public void updateDiceValue(int diceValue) {
        updateDiceValueImage(diceValue);
    }
}