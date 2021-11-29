package nomad.main.controller;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.scene.control.ListView;
import nomad.common.data_structure.Session;
import nomad.common.data_structure.UserLight;
import nomad.common.ihm.IhmControllerComponent;
import nomad.main.IhmMainScreenController;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuController extends IhmControllerComponent implements ListChangeListener {

    public ListView<String> userList;
    int counter = 0;
    private IhmMainScreenController ihmController;


    public MenuController(IhmMainScreenController ihmMainScreenController) {
        super(ihmMainScreenController);
        this.ihmController = ihmMainScreenController;
    }

    public void logout() {
        this.ihmController.getDataI().logout();
        this.ihmController.changeScreen(0);
    }

    public void onClickCreate() {
        ihmController.changeScreen(3);

    }

    public void onClickJoin() {

        ihmController.changeScreen(5);
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
