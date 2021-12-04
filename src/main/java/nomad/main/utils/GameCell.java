package nomad.main.utils;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import nomad.common.data_structure.GameLight;
import nomad.main.IhmMainScreenController;

import java.io.IOException;

public class GameCell extends ListCell<GameLight> {


    @FXML
    public Label playerName;
    @FXML
    public Label playerId;
    @FXML
    public Label towers;

    @FXML
    public Label gameName;

    public HBox container;

    public GameCell(IhmMainScreenController controller) {
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


    @Override
    protected void updateItem(GameLight item, boolean empty) {
        super.updateItem(item, empty);
        if(empty || item == null) {

            setText(null);
            setGraphic(null);

        }
        else {
            gameName.setText(item.getGameId().toString());
            playerId.setText(item.getHost().getId().toString());
            playerName.setText(item.getHost().getLogin());
            towers.setText(String.valueOf(item.getNbOfTowers()));
            setText(null);
            setGraphic(container);
        }
    }
}
