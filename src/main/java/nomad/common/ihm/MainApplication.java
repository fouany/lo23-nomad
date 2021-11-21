package nomad.common.ihm;

import javafx.application.Application;
import javafx.stage.Stage;
import nomad.com.client.ClientController;
import nomad.com.client.concrete.ComClientToDataConcrete;
import nomad.data.client.DataClientController;
import nomad.data.client.DataToComConcrete;
import nomad.data.client.DataToMainConcrete;
import nomad.game.IhmGameScreenController;
import nomad.main.IhmMainScreenController;
import nomad.main.IhmMainToDataConcrete;

import java.io.IOException;

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
    } catch (IOException e) {
      e.printStackTrace();
    }
    linkConcreteController();
  }

  public void initConcreteInterface () {
    dataToComConcrete = new DataToComConcrete();
    dataToMainConcrete = new DataToMainConcrete();

    ihmMainToDataConcrete = new IhmMainToDataConcrete();
    comClientToDataConcrete = new ComClientToDataConcrete();
  }

  public void initController () throws IOException {
    dataClientController = new DataClientController(comClientToDataConcrete,
            ihmMainToDataConcrete,
            null);
    clientController = new ClientController(dataToComConcrete);
    ihmGameScreenController = new IhmGameScreenController(this);
    ihmMainScreenController = new IhmMainScreenController(this, dataToMainConcrete);
  }

  public void linkConcreteController (){
    dataToComConcrete.setController(dataClientController);
    dataToMainConcrete.setController(dataClientController);

    ihmMainToDataConcrete.setController(ihmMainScreenController);
    comClientToDataConcrete.setController(clientController);
  }

  @Override
  public void start(Stage primaryStage) {
    stage = primaryStage;
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
