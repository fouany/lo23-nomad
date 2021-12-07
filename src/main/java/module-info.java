module nomadapp {
    requires java.sql;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    exports nomad.main.utils to javafx.fxml;
    opens nomad.main.utils to javafx.fxml;
    exports nomad.common.ihm;
    exports nomad.main.controller;
    exports nomad.game.controller;
    exports nomad.common;
    opens nomad.game.controller to javafx.fxml;
}
