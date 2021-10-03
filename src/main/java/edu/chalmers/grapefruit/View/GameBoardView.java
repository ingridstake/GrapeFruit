package edu.chalmers.grapefruit.View;

import edu.chalmers.grapefruit.Controller.GameBoardController;
import edu.chalmers.grapefruit.Interfaces.IPositionable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.util.List;

public class GameBoardView {

    @FXML AnchorPane background;

    /**
     * There need to be a constructor taking no arguments in order for the load of the View's .fxml-file to work
     */
    public GameBoardView () { }

    // TODO: Klura ut hur man får controllern att subscribea/lyssna på event/action hos någon av positionerna
    /**
     * Populates the GameBoardView with all objects in the positionableList.
     * @param positionableList is the list of positional objects that populates the view.
     * @param controller is the controller of the GameBoardView
     * @throws IOException
     */
    public void populate (List<IPositionable> positionableList, GameBoardController controller) throws IOException {
        for (IPositionable positionable : positionableList ) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(positionable.resourceString()));
            background.getChildren().add(fxmlLoader.load());
        }

        int i = 0;
        for (javafx.scene.Node child : background.getChildren()) {
            child.relocate(positionableList.get(i).getX(), positionableList.get(i).getY());
            i++;
            if (i>= positionableList.size()) {
                break;
            }
        }
    }

    @FXML
    public void initialize() throws IOException {
        //inte säkert att denna kommer till använding, men metoden körs efter att konstruktorn och annan setup körts,
        //
    }
}