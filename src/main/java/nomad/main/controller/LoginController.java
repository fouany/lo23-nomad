package nomad.main.controller;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import nomad.common.ihm.IhmControllerComponent;
import nomad.main.IhmMainScreenController;

public class LoginController extends IhmControllerComponent {
    public TextField login;
    public PasswordField password;

    private IhmMainScreenController ihmMainScreenController;

    public LoginController(IhmMainScreenController ihmMainScreenController) {
        super(ihmMainScreenController);
        this.ihmMainScreenController = ihmMainScreenController;
    }

    public void onClickImportProfile() {
    }

    public void onClickSignUp() {
    }

    public void onClickSignIn() {
        ihmMainScreenController.getAttributes().put("login", login.getText());
        ihmMainScreenController.getAttributes().put("password", password.getText());

        screenControl.changeScreen(1);
    }
}