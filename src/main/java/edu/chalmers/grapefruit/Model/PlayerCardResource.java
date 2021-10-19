package edu.chalmers.grapefruit.Model;

import edu.chalmers.grapefruit.Model.Player.IPlayer;
import edu.chalmers.grapefruit.Model.Player.PlayerColor;
import edu.chalmers.grapefruit.Utils.Observer;

public class PlayerCardResource {

    IPlayer player;

    public PlayerCardResource(IPlayer player) {
        this.player = player;
    }

    public int getMoneyBalance() {
        return player.getMoneyBalance();
    }

    public boolean hasCow() {
        return player.hasCow();
    }

    public boolean hasVisa() {
        return player.hasVisa();
    }

    public String getResourceString() {
        return PlayerColor.evaluateCardResourceString(player.getPlayerColor());
    }

    public void addPlayerObserver(Observer observer) {
        player.addObserver(observer);
    }
}
