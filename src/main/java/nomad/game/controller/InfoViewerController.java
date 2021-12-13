package nomad.game.controller;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import nomad.common.data_structure.ProfileStat;
import nomad.common.data_structure.User;
import nomad.common.ihm.IhmScreenController;
import nomad.data.client.DataToGameConcrete;
import nomad.game.IhmGameScreenController;

public class InfoViewerController extends GameComponentsAbstract {
    /**
     * Constructor that link the screen controller to the component controller
     *
     * @param screen
     */
    protected InfoViewerController(IhmScreenController screen) {
        super(screen);
    }

    @Override
    public void init() {
        IhmGameScreenController ihmGameScreenController = (IhmGameScreenController)super.screenControl;
        DataToGameConcrete dataInterface = ihmGameScreenController.getDataInterface();
        User user = dataInterface.getUser();
        String profilePicture = user.getProfilePicture();
        ProfileStat profileStats = user.getProfileStat();
        String login = user.getLogin();
        InfoViewerController.infoViewerControllerScene = ihmGameScreenController.getStage().getScene();

    }

    private static Scene infoViewerControllerScene = null;


    @Override
    public void update(String type, Object value) {
    }

    public static void display(String login, String profilePicture, ProfileStat profileStats, IhmScreenController controller)
    {

        Stage stage = new Stage();
        Stage primaryStage = controller.getStage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(InfoViewerController.infoViewerControllerScene);
        stage.initOwner(primaryStage);
        // Calculate the center position of the parent Stage
        double centerXPosition = primaryStage.getX() + primaryStage.getWidth()/2d;
        double centerYPosition = primaryStage.getY() + primaryStage.getHeight()/2d;

        // Hide the pop-up stage before it is shown and becomes relocated
        stage.setOnShowing(ev -> stage.hide());

        // Relocate the pop-up Stage
        stage.setOnShown(ev -> {
            stage.setX(centerXPosition - stage.getWidth()/2d);
            stage.setY(centerYPosition - stage.getHeight()/2d - 50);
            stage.show();
        });
        Button button = (Button) infoViewerControllerScene.lookup("#close");
        Label titleL = (Label) infoViewerControllerScene.lookup("#name");
        titleL.setText(login);
        Label scoreL = (Label) infoViewerControllerScene.lookup("#score");
        scoreL.setText(Integer.toString(profileStats.getGamesPlayed()));
        Label victoryL = (Label) infoViewerControllerScene.lookup("#victory");
        victoryL.setText(Integer.toString(profileStats.getGamesWon()));
        Label defeatL = (Label) infoViewerControllerScene.lookup("#defeat");
        defeatL.setText(Integer.toString(profileStats.getGamesLost()));

        //VBox container = (VBox) dialogScene.lookup("#container");

        button.setOnAction(e -> stage.close());
        stage.showAndWait();
    }

}

