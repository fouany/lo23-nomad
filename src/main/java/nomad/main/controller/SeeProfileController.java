package nomad.main.controller;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import nomad.common.data_structure.User;
import nomad.common.ihm.IhmControllerComponent;
import nomad.common.ihm.IhmScreenController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static nomad.main.controller.ModifyProfileController.convertToFxImage;
import static nomad.main.controller.ModifyProfileController.decodeImage;

public class SeeProfileController extends IhmControllerComponent {
    /**
     * Constructor that link the screen controller to the component controller
     *
     * @param screen
     */
    public SeeProfileController(IhmScreenController screen) {
        super(screen);
    }

    private static Scene scene = null;
    private static Pane dialogPane = null;


    public static void display(IhmScreenController controller, User user) {
        // Create the pane
        Stage stage = new Stage();
        Stage primaryStage = controller.getStage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        if(SeeProfileController.scene == null) {
            SeeProfileController.scene = new Scene(SeeProfileController.dialogPane);
        }
        stage.setScene(SeeProfileController.scene);
        stage.initOwner(primaryStage);

        //Center the pane
        DialogController.centerDialog(stage, primaryStage);

        //Recover the FXML ID's
        Button buttonClose = (Button) dialogPane.lookup("#close");
        Label labelUserName = (Label) dialogPane.lookup("#userName");
        Label labelBirthDate = (Label) dialogPane.lookup("#birthDate");
        Label labelUserLogin = (Label) dialogPane.lookup("#userLogin");
        Label labelUserId = (Label) dialogPane.lookup("#userId");
        Label labelNumberOfGames = (Label) dialogPane.lookup("#numberOfGames");
        Label labelNumberOfVictories = (Label) dialogPane.lookup("#numberOfVictories");
        Label labelNumberOfLoses = (Label) dialogPane.lookup("#numberOfLoses");
        Label labelNumberOfDraws = (Label) dialogPane.lookup("#numberOfDraws");
        Label labelNumberOfRatio = (Label) dialogPane.lookup("#numberOfRatio");
        ImageView profilePicture = (ImageView) dialogPane.lookup("#profilePicture");

        //Auxiliar variables
        int totalGames = user.getProfileStat().getGamesPlayed();
        int winGames = user.getProfileStat().getGamesWon();
        int lostGames = user.getProfileStat().getGamesLost();
        int drawGames = totalGames - lostGames - winGames;
        int ratio = 1;
        if (lostGames != 0)
            ratio = winGames / lostGames;
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        //Set all the text into the labels
        labelUserName.setText(user.getLogin());
        if (user.getBirthDate() != null)
            labelBirthDate.setText(dateFormat.format(user.getBirthDate()));
        labelUserLogin.setText(user.getLogin());
        labelUserId.setText(user.getUserId().toString());
        labelNumberOfGames.setText(String.valueOf(totalGames));
        labelNumberOfVictories.setText(String.valueOf(winGames));
        labelNumberOfLoses.setText(String.valueOf(lostGames));
        labelNumberOfDraws.setText(String.valueOf(drawGames));
        labelNumberOfRatio.setText(String.valueOf(ratio));

        //Set the profile picture
        byte[] byteArray = decodeImage(user.getProfilePicture());
        if(byteArray != null && byteArray.length > 0) {
            ByteArrayInputStream bis = new ByteArrayInputStream(decodeImage(user.getProfilePicture()));
            BufferedImage img = null;
            try {
                img = ImageIO.read(bis);
                profilePicture.setImage(convertToFxImage(img));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        buttonClose.setOnAction(e -> stage.close());
        stage.showAndWait();
    }

    public static void initDialog(Pane pane) {
        SeeProfileController.dialogPane = pane;
    }
}

