package nomad.common.ihm;

import javafx.application.Application;
import javafx.stage.Stage;
import nomad.game.IhmGameScreenController;
import nomad.main.IhmMainScreenController;

import java.io.IOException;

/**
 * Main application
 */
public class MainApplication extends Application {

  /**
   * Current stage
   */
  Stage stage;

  private final int MIN_WIDTH= 935;
  private final int MIN_HEIGHT = 610;

  @Override
  public void start(Stage primaryStage) throws Exception {
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
  public void changeModule(String mode) throws IOException {
    IhmScreenController controller;
    if (mode.equals("MAIN")) {
      controller = new IhmMainScreenController(this);
    } else {
      controller = new IhmGameScreenController(this);
    }
    controller.initIHM();
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
