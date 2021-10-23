package edu.chalmers.grapefruit.View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.event.EventHandler;

public class EndView {

    @FXML private AnchorPane endPane;
    @FXML Button exitButton;

    /**
     * Creates and returns an instance of EndView containing a loaded AnchorPane.
     * @return an EndView
     * @throws Exception if the filepath for the anchor pane is incorrect.
     */
    public static EndView createEndView() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(GameBoardView.class.getResource("end-view.fxml"));
        AnchorPane view = fxmlLoader.load();

        EndView endView = getEndViewController(view);
        endView.endPane = view;

        return endView;
    }

    /**
     * Returns a fxcontroller parsed to a EndView.
     * @param node is the node that holds the fxcontroller that should be an instance of EndView.
     * @return a fxcontroller parsed to a EndView.
     * @throws Exception if the fxController cannot be parsed to an EndView.
     */
    private static EndView getEndViewController(Node node) throws Exception {
        Object fxController = ViewUtils.getController(node);

        EndView endView = fxController instanceof EndView ? (EndView) fxController : null;
        if (endView == null){
            throw new Exception("The argument node's fx:controller is not an instance of EndView");
        }

        return endView;
    }

    /**
     * Lets the exitGameHandler listen to a click on the exitbutton.
     * @param exitGameHandler
     */
    public void populate(EventHandler exitGameHandler){
        exitButton.setOnAction(exitGameHandler);
    }

    /**
     * Returns the endPane.
     * @return the endPane.
     */
    public AnchorPane getEndPane() {
        return endPane;
    }
}