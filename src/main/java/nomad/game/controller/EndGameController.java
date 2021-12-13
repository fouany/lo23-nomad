package nomad.game.controller;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import nomad.common.data_structure.ProfileStat;
import nomad.common.data_structure.User;
import nomad.common.data_structure.UserLight;
import nomad.common.ihm.IhmScreenController;
import nomad.common.interfaces.data.DataToIhmGameInterface;
import nomad.data.client.DataToGameConcrete;
import nomad.game.IhmGameScreenController;

public class EndGameController extends GameComponentsAbstract {

    protected EndGameController(IhmScreenController screen) {
        super(screen);
    }


    @Override
    public void update(String type, Object value) {
    }
    private static Scene EndGameControllerScene = null;


    @Override
    public void init() {
        IhmGameScreenController ihmGameScreenController = (IhmGameScreenController)super.screenControl;
        DataToGameConcrete dataInterface = ihmGameScreenController.getDataInterface();
        UserLight userLight = dataInterface.getUserLight();
        String login = userLight.getLogin();
        EndGameController.EndGameControllerScene = ihmGameScreenController.getStage().getScene();
    }

    public void display(String login, IhmScreenController controller) {

        IhmGameScreenController ihmGameScreenController = (IhmGameScreenController) super.screenControl;
        DataToGameConcrete dataInterface = ihmGameScreenController.getDataInterface();
        Stage stage = new Stage();
        Stage primaryStage = controller.getStage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(EndGameController.EndGameControllerScene);
        stage.initOwner(primaryStage);
        // Calculate the center position of the parent Stage
        double centerXPosition = primaryStage.getX() + primaryStage.getWidth() / 2d;
        double centerYPosition = primaryStage.getY() + primaryStage.getHeight() / 2d;

        // Hide the pop-up stage before it is shown and becomes relocated
        stage.setOnShowing(ev -> stage.hide());

        // Relocate the pop-up Stage
        stage.setOnShown(ev -> {
            stage.setX(centerXPosition - stage.getWidth() / 2d);
            stage.setY(centerYPosition - stage.getHeight() / 2d - 50);
            stage.show();
        });
        Button buttonYes = (Button) EndGameControllerScene.lookup("#yes");
        Button buttonNo = (Button) EndGameControllerScene.lookup("#no");
        //Label winnerL = (Label) EndGameControllerScene.lookup("#winner");


        //VBox container = (VBox) dialogScene.lookup("#container");

        buttonNo.setOnAction(e -> stage.close());
        buttonYes.setOnAction(e ->
        {
            dataInterface.saveCurrentGame();
            stage.close();
        });
        stage.showAndWait();
    }

}

