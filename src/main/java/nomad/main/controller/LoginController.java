package nomad.main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import nomad.common.ihm.IhmControllerComponent;
import nomad.main.IhmMainScreenController;

public class LoginController extends IhmControllerComponent {
    @FXML
    public TextField login;
    @FXML
    public PasswordField password;
    private boolean isSignup ;

    private IhmMainScreenController ihmMainScreenController;

    public LoginController(IhmMainScreenController ihmMainScreenController) {
        super(ihmMainScreenController);
        this.ihmMainScreenController = ihmMainScreenController;
        this.isSignup = false;
    }

    public void onClickImportProfile() {
        // TODO : implement method
    }

    public void onClickSignUp() {
        // TODO : implement method
        isSignup = true;
        ihmMainScreenController.getAttributes().put("isSignup", String.valueOf(isSignup));
        ihmMainScreenController.getAttributes().put("login", login.getText());
        ihmMainScreenController.getAttributes().put("password", password.getText());
        screenControl.changeScreen(1);
    }

    public void onClickSignIn() {
        isSignup = false;
        ihmMainScreenController.getAttributes().put("isSignup", String.valueOf(isSignup));
        ihmMainScreenController.getAttributes().put("login", login.getText());
        ihmMainScreenController.getAttributes().put("password", password.getText());
        screenControl.changeScreen(1);
    }
}