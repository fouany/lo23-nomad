package nomad.main.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import nomad.common.data_structure.GameLight;
import nomad.main.IhmMainScreenController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameCell extends ListCell<GameLight> {


    @FXML
    public Label playerName;
    @FXML
    public Label playerId;
    @FXML
    public Label towers;

    @FXML
    public Label gameName;

    @FXML
    public HBox container;

    private GameLight game;

    private final IhmMainScreenController controller;

    public GameCell(IhmMainScreenController controller) {
        this.controller = controller;
        loadFXML(controller);
    }

    private void loadFXML(IhmMainScreenController controller) {
        try {

            FXMLLoader loader =new FXMLLoader(controller.getClass().getResource("fxml/ihm_game_cell.fxml"),null);

            loader.setController(this);
            loader.load();
        }
        catch (IOException e) {
            e.printStackTrace();


        }
    }
    @FXML
    public void joinGame(ActionEvent e)
    {
        Logger.getAnonymousLogger().log(Level.INFO, "CLICK");

        controller.getViewGameController().blockIhm();
        controller.getComI().addPlayerInGame(controller.getDataI().getPlayer(),game);



    }

    @Override
    protected void updateItem(GameLight item, boolean empty) {



        if(empty || item == null) {

            setText(null);
            setGraphic(null);

        }
        else {
            game = item;
            gameName.setText(item.getGameId().toString());
            playerId.setText(item.getHost().getId().toString());
            playerName.setText(item.getHost().getLogin());
            towers.setText(String.valueOf(item.getNbOfTowers()));
            setText(null);
            setGraphic(container);
        }
    }
}
