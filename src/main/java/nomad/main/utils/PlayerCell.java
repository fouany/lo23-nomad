package nomad.main.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import nomad.common.data_structure.GameLight;
import nomad.common.data_structure.ProfileStat;
import nomad.common.data_structure.User;
import nomad.common.data_structure.UserLight;
import nomad.main.IhmMainScreenController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerCell extends ListCell<UserLight> {

    @FXML
    public Label name;
    @FXML
    public Label id;
    @FXML
    public HBox container;
    @FXML
    public Button see;


    private final IhmMainScreenController controller;

    private UserLight u;

    public PlayerCell(IhmMainScreenController controller) {
        this.controller = controller;
        loadFXML(controller);
        see.setOnAction(this::seeProfile);
    }

    /**
     * load fxml component for the game cell
     * @param controller use to locate and load the ressource
     */
    private void loadFXML(IhmMainScreenController controller) {
        try {

            FXMLLoader loader =new FXMLLoader(controller.getClass().getResource("fxml/ihm_profile_cell.fxml"),null);

            loader.setController(this);
            loader.load();
        }
        catch (IOException e) {
            e.printStackTrace();


        }
    }

    @FXML
    public void seeProfile(ActionEvent e)
    {

        if(this.u != null)
        {
            controller.getDataI().getProfileInfos(u.getId());
        }
    }

    @Override
    protected void updateItem(UserLight user, boolean empty) {

        super.updateItem(user, empty);

        if(empty || user == null) {

            setText(null);
            setGraphic(null);

        }
        else {
            u = user;
            name.setText(user.getLogin());
            id.setText(user.getId().toString());
            setText(null);
            setGraphic(container);
        }
    }
}
