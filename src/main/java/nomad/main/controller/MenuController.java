package nomad.main.controller;

import javafx.scene.control.ListView;
import nomad.common.data_structure.Session;
import nomad.common.data_structure.UserLight;
import nomad.common.ihm.IhmControllerComponent;
import nomad.main.IhmMainScreenController;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuController extends IhmControllerComponent implements Observer {

    public ListView<String> userList;
    int counter = 0;
    private IhmMainScreenController ihmController;


    public MenuController(IhmMainScreenController ihmMainScreenController) {
        super(ihmMainScreenController);
        this.ihmController = ihmMainScreenController;
    }

    public void logout() {
        this.ihmController.getDataI().logout();
    }

    public void onClickCreate() {
        counter++;
        String name = "user_" + counter;
        userList.getItems().add(name);
    }

    public void onClickJoin() {
        // TODO : implement method
    }

    public void onClickProfile() {
        // TODO : implement method
    }

    @Override
    public void update(Observable o, Object arg) {
        Session session = null;
        if (o instanceof Session) {
            session = (Session) o;
        }
        if (session != null) {
            List<UserLight> connectedUsers = session.getConnectedUsers();
            userList.getItems().clear();
            displayUser(connectedUsers, userList);
        }

    }

    public void displayUser(List<UserLight> users, ListView<String> view) {
        for (UserLight user : users) {
            String field = user.getLogin() + "#" + user.getId();
            view.getItems().add(field);
        }
    }

    public void handleUserListClick() {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Display profile of " + userList.getSelectionModel().getSelectedItem());
    }
}
