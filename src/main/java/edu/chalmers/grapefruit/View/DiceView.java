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

/**
 *
 */
public class DiceView implements DiceRolledListener {

    private @FXML ImageView diceValueImage;
    private Image dice1 = new Image("edu/chalmers/grapefruit/View/images/dice2.png");
    private Image dice2 = new Image("edu/chalmers/grapefruit/View/images/dice2.png");
    private Image dice3 = new Image("edu/chalmers/grapefruit/View/images/dice3.png");
    private Image dice4 = new Image("edu/chalmers/grapefruit/View/images/dice4.png");
    private Image dice5 = new Image("edu/chalmers/grapefruit/View/images/dice5.png");
    private Image dice6 = new Image("edu/chalmers/grapefruit/View/images/dice6.png");
    Node node;

    public static DiceView getDiceViewController(Node node) throws IOException, Exception {

        Object controllerObject = GameBoardView.getController(node);

        DiceView diceView = controllerObject instanceof DiceView ? (DiceView) controllerObject : null;

        if (diceView == null) {
            throw new Exception("The argument node's fx:controller is not an instance of DiceView");
        }
        return diceView;
    }

    public static DiceView createDiceView () throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(GameBoardView.class.getResource("dice-view.fxml"));
        Node node =  fxmlLoader.load();
        DiceView diceView = getDiceViewController(node);
        diceView.setNode(node);

        return diceView;
    }

    public Node getNode() {
        return node;
    }

    /*
    public Node getDiceNode () throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(GameBoardView.class.getResource("dice-view.fxml"));
        Node dice =  fxmlLoader.load();
        return dice;
    }

     */

    public void updateDiceValueImage(int diceValue) {
        switch (diceValue) {
            case 1:
                diceValueImage.setImage(dice1);
                break;
            case 2:
                diceValueImage.setImage(dice2);
                break;
            case 3:
                diceValueImage.setImage(dice3);
                break;
            case 4:
                diceValueImage.setImage(dice4);
                break;
            case 5:
                diceValueImage.setImage(dice5);
                break;
            case 6:
                diceValueImage.setImage(dice6);
                break;
            default:
                diceValueImage.setVisible(false);
        }
        diceValueImage.setVisible(true);
        System.out.println("detta är tärningsvärdet: " + diceValue);
    }

    @Override
    public void updateDiceValue(int diceValue) {
        System.out.println("kommer vi hit?");
        updateDiceValueImage(diceValue);
    }
}
