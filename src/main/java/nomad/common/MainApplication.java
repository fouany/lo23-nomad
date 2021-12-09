package nomad.common;

import javafx.application.Application;
import javafx.stage.Stage;
import nomad.com.client.ClientController;
import nomad.com.client.concrete.ComClientToDataConcrete;
import nomad.com.client.concrete.ComClientToIhmMainConcrete;
import nomad.common.ihm.IhmScreenController;
import nomad.data.client.DataClientController;
import nomad.data.client.DataToComConcrete;
import nomad.data.client.DataToMainConcrete;
import nomad.game.IhmGameScreenController;
import nomad.main.IhmMainScreenController;
import nomad.main.IhmMainToDataConcrete;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main application
 */
public class MainApplication extends Application {

  /**
   * Current stage
   */
  Stage stage;
  private DataToMainConcrete dataToMainConcrete;
  private DataToComConcrete dataToComConcrete;
  private ComClientToIhmMainConcrete comClientToIhmMainConcrete;
  private IhmMainToDataConcrete ihmMainToDataConcrete;
  private ComClientToDataConcrete comClientToDataConcrete;

  private DataClientController dataClientController;
  private ClientController clientController;
  private IhmMainScreenController ihmMainScreenController;
  private IhmGameScreenController ihmGameScreenController;
  private IhmScreenController screenController;

  public MainApplication() {
    initConcreteInterface();
    try {
      initController();
    } catch (Exception e) {
      Logger.getLogger(MainApplication.class.getName()).log(Level.WARNING, "Error caused by {0}.", e.toString());
    }
    linkConcreteController();
  }

  public void initConcreteInterface () {
    dataToComConcrete = new DataToComConcrete();
    dataToMainConcrete = new DataToMainConcrete();

    ihmMainToDataConcrete = new IhmMainToDataConcrete();
    comClientToIhmMainConcrete = new ComClientToIhmMainConcrete();
    comClientToDataConcrete = new ComClientToDataConcrete();
  }

  public void initController () throws IOException {
    dataClientController = new DataClientController(comClientToDataConcrete,
            ihmMainToDataConcrete,
            null);
    clientController = new ClientController(dataToComConcrete);
    ihmGameScreenController = new IhmGameScreenController(this);
    ihmMainScreenController = new IhmMainScreenController(this, dataToMainConcrete, comClientToIhmMainConcrete);
  }

  public void linkConcreteController (){
    dataToComConcrete.setController(dataClientController);
    dataToMainConcrete.setController(dataClientController);

    ihmMainToDataConcrete.setController(ihmMainScreenController);
    comClientToDataConcrete.setController(clientController);
  }

  private final int MIN_WIDTH= 935;
  private final int MIN_HEIGHT = 610;

  @Override
  public void start(Stage primaryStage) {
    stage = primaryStage;
    stage.setMinHeight(MIN_HEIGHT);
    stage.setMinWidth(MIN_WIDTH);
    this.changeModule("MAIN");
  }

  /**
   * Change the current module (Main of Game)
   * @param mode module wanted
   * @throws IOException
   */
  public void changeModule(String mode) {

    if (mode.equals("MAIN")) {
      screenController = ihmMainScreenController;
    } else {
      screenController = ihmGameScreenController;
    }
    screenController.initIHM();
  }

  /**
   * Get the current stage
   * @return the stage
   */
  public Stage getStage(){
    return stage;
  }

  /**
   * Entry point of the application
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }


}
