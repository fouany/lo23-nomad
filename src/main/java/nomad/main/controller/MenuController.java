package nomad.main.controller;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import nomad.common.MainApplication;
import nomad.common.data_structure.User;
import nomad.common.data_structure.UserLight;
import nomad.common.ihm.IhmControllerComponent;
import nomad.main.IhmMainScreenController;
import nomad.main.IhmMainToComConcrete;
import nomad.main.utils.PlayerCell;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class MenuController extends IhmControllerComponent implements ListChangeListener<UserLight>, Initializable {

    @FXML
    public ListView<UserLight> userList;

    public List<User> bigUserList = new ArrayList<>();
    private IhmMainScreenController ihmController;

    public MenuController(IhmMainScreenController ihmMainScreenController) {
        super(ihmMainScreenController);
        this.ihmController = ihmMainScreenController;
    }

    public void logout() {
        this.ihmController.getDataI().logout();
        this.ihmController.changeScreen(LoginController.class);
        if (this.ihmController.getSession() != null) {
            this.ihmController.getSession().getConnectedUsers().removeListener(this);
        }
        if (this.ihmController.getViewGameController() != null) {
            this.ihmController.getSession().getGamesInPlay().removeListener(ihmController.getViewGameController().gamesAsViewer);
            this.ihmController.getSession().getGamesInLobby().removeListener(ihmController.getViewGameController().gamesAsPlayer);
        }
    }

    public void onClickCreate() {
        ihmController.changeScreen(CreateGameController.class);
    }

    public void onClickJoin() {
        ihmController.changeScreen(ViewGameController.class);
    }

    public void onClickProfile() {
        ihmController.getProfileController().start();
        ihmController.changeScreen(ModifyProfileController.class);

    }

    private void addUserInList(UserLight u)
    {
        Platform.runLater(() -> userList.getItems().add(u));
    }

    private void removeUser(UserLight u)
    {
        Platform.runLater(() -> userList.getItems().remove(u));
    }


    /*public void removeUser(Object u) {
        String field = ((UserLight) u).getLogin() + "#" + ((UserLight) u).getId();
        userList.getItems().remove(field);
    }*/

    public void handleUserListClick() {
        /*if(userList.getSelectionModel().getSelectedItem() != null) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, userList.getSelectionModel().getSelectedItem());

            // On obtient le UUID de l'utilisateur cliqué
            String[] userInfo = userList.getSelectionModel().getSelectedItem().split("#");
            UUID userID = UUID.fromString(userInfo[1]);
            this.ihmController.getDataI().getProfileInfos(userID);

            //SeeProfileController.display(ihmController, userList.getSelectionModel().getSelectedItem());
        }*/
    }

    @Override
    public void onChanged(Change change) {
        while (change.next()) {

            if (change.wasAdded()) {
                for (Object u : change.getAddedSubList()) {
                    addUserInList((UserLight) u);
                }
            } else if (change.wasRemoved()) {
                for (Object u : change.getRemoved()) {

                    removeUser((UserLight)u);
                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        userList.setCellFactory(lv -> new PlayerCell(ihmController));
    }
}
