module edu.chalmers.grapefruit {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens edu.chalmers.grapefruit to javafx.fxml;
    exports edu.chalmers.grapefruit;
    exports edu.chalmers.grapefruit.Controller;
    opens edu.chalmers.grapefruit.Controller to javafx.fxml;
}