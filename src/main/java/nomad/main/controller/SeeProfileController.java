package nomad.main.controller;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import nomad.common.data_structure.User;
import nomad.common.ihm.IhmControllerComponent;
import nomad.common.ihm.IhmScreenController;

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
        Stage stage = new Stage();
        Stage primaryStage = controller.getStage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        if(SeeProfileController.scene == null) {
            SeeProfileController.scene = new Scene(SeeProfileController.dialogPane);
        }
        stage.setScene(SeeProfileController.scene);
        stage.initOwner(primaryStage);

        DialogController.centerDialog(stage, primaryStage);


        Button buttonClose = (Button) dialogPane.lookup("#close");
        Label labelUserName = (Label) dialogPane.lookup("#userName");
        Label labelUserID = (Label) dialogPane.lookup("#userID");
        Label labelNumberOfGames = (Label) dialogPane.lookup("#numberOfGames");
        Label labelNumberOfVictories = (Label) dialogPane.lookup("#numberOfVictories");
        Label labelNumberOfLoses = (Label) dialogPane.lookup("#numberOfLoses");
        Label labelNumberOfDraws = (Label) dialogPane.lookup("#numberOfDraws");
        Label labelNumberOfRatio = (Label) dialogPane.lookup("#numberOfRatio");
        labelUserName.setText("user.getLogin()");
        labelUserID.setText("user.getUserId())");
        labelNumberOfGames.setText("666");
        labelNumberOfVictories.setText("66");
        labelNumberOfLoses.setText("66");
        labelNumberOfDraws.setText("66");
        labelNumberOfRatio.setText("1.0");

        buttonClose.setOnAction(e -> stage.close());
        stage.showAndWait();
    }

    public static void initDialog(Pane pane) {
        SeeProfileController.dialogPane = pane;
    }
}

