module clueless.zombiesattack {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens clueless.zombiesattack to javafx.fxml;
    exports clueless.zombiesattack;
}