package nomad.main.controller;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import nomad.common.data_structure.UserException;
import nomad.common.ihm.IhmControllerComponent;
import nomad.main.IhmMainScreenController;
import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CreateProfileController extends IhmControllerComponent {

    @FXML
    public TextField createLogin;
    @FXML
    public TextField createName;
    @FXML
    public PasswordField createPassword;
    @FXML
    public DatePicker addBirthDate;
    @FXML
    public ImageView addProfilePicture;

    public static String encodeImage(byte[] imageByteArray) {
        return Base64.encodeBase64URLSafeString(imageByteArray);
    }
    private IhmMainScreenController ihmMainScreenController;
    public CreateProfileController(IhmMainScreenController ihmMainScreenController) {
        super(ihmMainScreenController);
        this.ihmMainScreenController = ihmMainScreenController;
    }

    private String profilePictureStr;

    public String getProfilePicture() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(this.ihmMainScreenController.getStage());
        try (FileInputStream fs = new FileInputStream(file)) {
            byte[] imageData = new byte[(int) file.length()];

            if (fs.read(imageData) > 0) {
                Image img = new Image(file.toURI().toString());
                addProfilePicture.setImage(img);
                this.profilePictureStr = encodeImage(imageData);
                return profilePictureStr;
            }
        } catch (IOException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
        }
        return "";
    }
    public void onClickCreateProfile() {
        Date dateBirth = Date.from(addBirthDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        try {
            this.ihmMainScreenController.getDataI().createAccount(createLogin.getText(), createPassword.getText(), createName.getText(), profilePictureStr, dateBirth);
        } catch (UserException | IOException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
            DialogController.display("Create User", "Le profil n'a pas bien été créé", DialogController.DialogStatus.ERROR, this.ihmMainScreenController);
        }
        this.ihmMainScreenController.getAttributes().put("login",createLogin.getText());
        this.ihmMainScreenController.getAttributes().put("password",createPassword.getText());
        screenControl.changeScreen(ServerConnectionController.class);
    }
    public void onClickReturnToLogin(){
        this.ihmMainScreenController.changeScreen(LoginController.class);
    }
}
