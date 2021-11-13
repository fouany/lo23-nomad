module nomadapp {
    requires java.sql;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    exports nomad.common.ihm;
    exports nomad.main.resources.controller;
    exports nomad.game.resources.controller;
}