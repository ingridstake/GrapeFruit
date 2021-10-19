package edu.chalmers.grapefruit.View;

import edu.chalmers.grapefruit.Model.PlayerCardResource;
import edu.chalmers.grapefruit.Utils.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class PlayerCardView implements Observer {

    private PlayerCardResource playerCardResource;

    @FXML private Label moneyBalanceLabel1;
    @FXML private ImageView cowImage1;
    @FXML private ImageView cowbellImage1;

    public PlayerCardView() {}
    /*
    public PlayerCardView() {
        cowbellImage1.setVisible(false);
        cowImage1.setVisible(false);
    }

     */

    public void setPlayerCardResource(PlayerCardResource playerCardResource) {
        this.playerCardResource = playerCardResource;
        update();
    }

    @Override
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
