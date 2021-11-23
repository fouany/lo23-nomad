package nomad.main.controller;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.scene.control.ListView;
import nomad.common.data_structure.UserLight;
import nomad.common.ihm.IhmControllerComponent;
import nomad.main.IhmMainScreenController;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuController extends IhmControllerComponent implements ListChangeListener {

    public ListView<String> userList;
    private IhmMainScreenController ihmController;


    public MenuController(IhmMainScreenController ihmMainScreenController) {
        super(ihmMainScreenController);
        this.ihmController = ihmMainScreenController;
    }

    public void logout() {
        try {
            this.ihmController.getDataI().logout();
            this.ihmController.changeScreen(0);
        } catch (IOException e) {
            // TODO : display something !
        }
    }

    public void onClickCreate() {
        // TODO : implement method
    }

    public void onClickJoin() {
        // TODO : implement method
    }

    public void onClickProfile() {
        // TODO : implement method
    }

    public void displayUser(List<UserLight> users, ListView<String> view) {
        for (UserLight user : users) {
            String field = user.getLogin() + "#" + user.getId();
            view.getItems().add(field);
        }
    }

    public void handleUserListClick() {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, userList.getSelectionModel().getSelectedItem());
    }

    @Override
    public void onChanged(Change change) {
        while (change.next()) {

            if (change.wasAdded()) {
                for (Object u : change.getAddedSubList()) {
                    Platform.runLater(() -> {
                        userList.getItems().add(((UserLight) u).getLogin()); // add new user login to userList
                    });
                }
            } else if (change.wasRemoved()) {
                for (Object u : change.getRemoved()) {
                    Platform.runLater(() -> {
                        userList.getItems().remove(((UserLight) u).getLogin()); // remove user with login
                    });
                }
            }
        }
    }
}
