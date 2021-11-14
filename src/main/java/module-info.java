module nomadapp {
    requires java.sql;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    exports nomad.common.ihm;
    exports nomad.main.controller;
    exports nomad.game.controller;
}