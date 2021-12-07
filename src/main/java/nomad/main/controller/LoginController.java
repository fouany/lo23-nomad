package nomad.main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import nomad.common.data_structure.UserException;
import nomad.common.ihm.IhmControllerComponent;
import nomad.main.IhmMainScreenController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController extends IhmControllerComponent {
    @FXML
    public TextField login;
    @FXML
    public PasswordField password;

    private IhmMainScreenController ihmMainScreenController;

    public LoginController(IhmMainScreenController ihmMainScreenController) {
        super(ihmMainScreenController);
        this.ihmMainScreenController = ihmMainScreenController;
    }

    public void onClickImportProfile() {
        // TODO : implement method
    }

    public void onClickSignUp() {
        // TODO : implement method
        String pass = password.getText();
        String username = login.getText();
        try {
            ihmMainScreenController.getDataI().createAccount(username,pass,username,"", null);
        } catch (IOException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, "Impossible to write a file");
        } catch (UserException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, "User doesnt match");
        }
        ihmMainScreenController.getAttributes().put("login", login.getText());
        ihmMainScreenController.getAttributes().put("password", password.getText());
        screenControl.changeScreen(1);
    }

    public void onClickSignIn() {
        ihmMainScreenController.getAttributes().put("login", login.getText());
        ihmMainScreenController.getAttributes().put("password", password.getText());
        screenControl.changeScreen(1);
    }
}