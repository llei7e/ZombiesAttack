module game.clueless {
    requires javafx.controls;
    requires javafx.fxml;


    opens game.clueless to javafx.fxml;
    exports game.clueless;
}