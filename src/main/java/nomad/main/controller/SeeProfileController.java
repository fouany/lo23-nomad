package nomad.main.controller;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import nomad.common.data_structure.ProfileStat;
import nomad.common.data_structure.User;
import nomad.common.ihm.IhmControllerComponent;
import nomad.common.ihm.IhmScreenController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;


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

        Button buttonClose = (Button) dialogPane.lookup("#back");
        Label labelUserName = (Label) dialogPane.lookup("#name");
        Label labelUserID = (Label) dialogPane.lookup("#id");
        Label labelNumberOfGames = (Label) dialogPane.lookup("#total");
        Label labelNumberOfVictories = (Label) dialogPane.lookup("#win");
        Label labelNumberOfLoses = (Label) dialogPane.lookup("#loses");
        Label labelNumberOfDraws = (Label) dialogPane.lookup("#draws");
        Label labelNumberOfRatio = (Label) dialogPane.lookup("#ratio");
        labelUserName.setText(user.getLogin());
        labelUserID.setText(user.getUserId().toString());
        ProfileStat stats = user.getProfileStat();
        labelNumberOfGames.setText(String.valueOf(stats.getGamesPlayed()));
        labelNumberOfVictories.setText(String.valueOf(stats.getGamesWon()));
        labelNumberOfLoses.setText(String.valueOf(stats.getGamesLost()));
        labelNumberOfDraws.setText(String.valueOf(stats.getGamesPlayed() - (stats.getGamesWon() + stats.getGamesLost())));
        if(stats.getGamesLost() == 0)
        {
            labelNumberOfRatio.setText("NONE");
        }
        else labelNumberOfRatio.setText(String.valueOf(stats.getGamesWon()/stats.getGamesLost()));

        ImageView profilePicture = (ImageView) dialogPane.lookup("#profilePicture");

        //Set the profile picture
        byte[] byteArray = ModifyProfileController.decodeImage(user.getProfilePicture());
        if(byteArray != null && byteArray.length > 0) {
            ByteArrayInputStream bis = new ByteArrayInputStream(ModifyProfileController.decodeImage(user.getProfilePicture()));
            BufferedImage img;
            try {
                img = ImageIO.read(bis);
                profilePicture.setImage(ModifyProfileController.convertToFxImage(img));
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

