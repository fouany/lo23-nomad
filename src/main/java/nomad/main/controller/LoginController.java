package nomad.main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import nomad.common.data_structure.UserException;
import nomad.common.ihm.IhmControllerComponent;
import nomad.main.IhmMainScreenController;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    FileChooser fileChooser = new FileChooser();

    public void onClickImportProfile() {
        // TODO : implement method
        fileChooser.setTitle("Import profile");
        File file = fileChooser.showOpenDialog(this.ihmMainScreenController.getStage());
        try {
            ihmMainScreenController.getDataI().addAccount(file.getPath());
        } catch (IOException | ClassNotFoundException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
        } catch (UserException e) {
            DialogController.display("Erreur", "L'utilisateur existe déjà en local.", DialogController.DialogStatus.ERROR, ihmMainScreenController);
        }
    }

    public void onClickSignUp() {
        // TODO : implement method
        isSignup = true;
        ihmMainScreenController.getAttributes().put("isSignup", String.valueOf(isSignup));
        ihmMainScreenController.getAttributes().put("login", login.getText());
        ihmMainScreenController.getAttributes().put("password", password.getText());
        screenControl.changeScreen(ServerConnectionController.class);
    }

    public void onClickSignIn() {
        isSignup = false;
        ihmMainScreenController.getAttributes().put("isSignup", String.valueOf(isSignup));
        ihmMainScreenController.getAttributes().put("login", login.getText());
        ihmMainScreenController.getAttributes().put("password", password.getText());
        screenControl.changeScreen(ServerConnectionController.class);
    }
}