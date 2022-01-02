module nomadapp {
    // Library dependencies
    requires java.sql;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.codec;
    requires java.desktop;

    // Common
    exports nomad.common;
    exports nomad.common.ihm;

    // Main module
    opens nomad.main.utils;
    opens nomad.main.controller;

    // Com module

    // Data module

    // Game module
    opens nomad.game;
    opens nomad.game.controller;
}
