package nomad.main.controller;

import javafx.scene.control.ListView;
import nomad.common.data_structure.Session;
import nomad.common.data_structure.UserLight;
import nomad.common.ihm.IhmControllerComponent;
import nomad.main.IhmMainScreenController;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MenuController extends IhmControllerComponent implements Observer {

    public ListView userList;
    int counter = 0;
    private IhmMainScreenController ihmController;


    public MenuController(IhmMainScreenController ihmMainScreenController) {
        super(ihmMainScreenController);
        this.ihmController = ihmMainScreenController;
        //dataMainImpl.getSession().addObserver(this);
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

    }

    public void onClickProfile() {
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

    public void displayUser(List<UserLight> users, ListView view) {
        for (UserLight user : users) {
            String field = user.getLogin() + "#" + user.getId();
            view.getItems().add(field);
        }
    }

    public void handleUserListClick() {
        //launch profile
        System.out.println("Display profile of " + userList.getSelectionModel().getSelectedItem());
    }
}
