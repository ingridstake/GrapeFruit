package edu.chalmers.grapefruit.Utils;

import edu.chalmers.grapefruit.Model.GameModel;
import edu.chalmers.grapefruit.Model.Player.IPlayer;
import edu.chalmers.grapefruit.Model.Position.IPosition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ingrid.stake
 * @author tovenilsson
 */

public class ViewEntityFactory {

    /**
     * This is a list of all game board entities that is to be displayed.
     */
    private static List<ViewEntity> viewEntities = new ArrayList<>();

    /**
     * Adds a new view entity to the static viewEntityList
     * @param resource is the resource of the view entity that is to be added.
     */
    public static void addEntity(ViewEntityResource resource) {
        viewEntities.add(new ViewEntity(resource));
    }

    public static List<ViewEntity> getViewEntities() {
        return viewEntities;
    }

    /**
     * Clears the view entity list.
     */
    public static void clearViewEntityFactory(){
        viewEntities = new ArrayList<>();
    }

    /**
     * Adds positions and players from game model and game board to the view entities list.
     * @param gameModel is the game model.
     * @return list of view entities.
     */
    public static List<ViewEntity> createViewEntities(GameModel gameModel){

        for (IPosition position: gameModel.getGameLogic().getGameBoard().getPositionList()) {
            addEntity(position);
        }
        for (IPlayer player: gameModel.getPlayers()) {
            addEntity(player);
        }
        return viewEntities;
    }
}