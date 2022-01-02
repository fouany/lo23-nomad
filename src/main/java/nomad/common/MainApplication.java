package nomad.common;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import nomad.com.client.ClientController;
import nomad.com.client.concrete.ComClientToDataConcrete;
import nomad.com.client.concrete.ComClientToIhmMainConcrete;
import nomad.com.client.concrete.ComToIhmGameConcrete;
import nomad.common.ihm.IhmScreenController;
import nomad.common.ihm.ModuleMode;
import nomad.common.interfaces.game.IhmGameToDataInterface;
import nomad.data.client.DataClientController;
import nomad.data.client.DataToComConcrete;
import nomad.data.client.DataToGameConcrete;
import nomad.data.client.DataToMainConcrete;
import nomad.game.IhmGameScreenController;
import nomad.game.IhmGameToComConcrete;
import nomad.game.IhmGameToDataConcrete;
import nomad.main.IhmMainScreenController;
import nomad.main.IhmMainToComConcrete;
import nomad.main.IhmMainToDataConcrete;
import nomad.main.controller.MenuController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main application
 */
public class MainApplication extends Application {

    private final int MIN_WIDTH = 935;
    private final int MIN_HEIGHT = 610;
    /**
     * Current stage
     */
    Stage stage;
    private DataToMainConcrete dataToMainConcrete;
    private DataToComConcrete dataToComConcrete;
    private DataToGameConcrete dataToGameConcrete;
    private ComClientToIhmMainConcrete comClientToIhmMainConcrete;
    private IhmMainToDataConcrete ihmMainToDataConcrete;
    private IhmMainToComConcrete ihmMainToComConcrete;
    private IhmGameToComConcrete ihmGameToComConcrete;
    private ComClientToDataConcrete comClientToDataConcrete;
    private DataClientController dataClientController;
    private ClientController clientController;
    private IhmMainScreenController ihmMainScreenController;
    private IhmGameScreenController ihmGameScreenController;
    private IhmScreenController screenController;
    private ComToIhmGameConcrete comToGameConcrete;
    private IhmGameToDataConcrete ihmGameToDataConcrete;

    public MainApplication() throws IOException {
        initConcreteInterface();
        initController();
        ihmMainToComConcrete.setController(ihmMainScreenController);
        linkConcreteController();
    }

    /**
     * Entry point of the application
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void initConcreteInterface() {
        dataToComConcrete = new DataToComConcrete();
        dataToMainConcrete = new DataToMainConcrete();
        dataToGameConcrete = new DataToGameConcrete();
        comToGameConcrete = new ComToIhmGameConcrete();
        ihmMainToDataConcrete = new IhmMainToDataConcrete();
        ihmMainToComConcrete = new IhmMainToComConcrete();
        comClientToIhmMainConcrete = new ComClientToIhmMainConcrete();
        comClientToDataConcrete = new ComClientToDataConcrete();
        ihmGameToDataConcrete = new IhmGameToDataConcrete();
        ihmGameToComConcrete = new IhmGameToComConcrete();
    }

    public void initController() throws IOException {
        dataClientController = new DataClientController(comClientToDataConcrete,
                ihmMainToDataConcrete,
                ihmGameToDataConcrete);
        dataToGameConcrete.setDataClientController(dataClientController);
        clientController = new ClientController(dataToComConcrete, ihmMainToComConcrete, ihmGameToComConcrete);
        ihmMainScreenController = new IhmMainScreenController(this, dataToMainConcrete, comClientToIhmMainConcrete);
    }

    public void linkConcreteController() {
        dataToComConcrete.setController(dataClientController);
        dataToMainConcrete.setController(dataClientController);
        ihmMainToComConcrete.setController(ihmMainScreenController);

        comClientToIhmMainConcrete.setClientController(clientController);
        ihmMainToDataConcrete.setController(ihmMainScreenController);
        comClientToDataConcrete.setController(clientController);
        comToGameConcrete.setController(clientController);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        attachCloseListener();
        stage.setMinHeight(MIN_HEIGHT);
        stage.setMinWidth(MIN_WIDTH);
        this.changeModule(ModuleMode.MAIN_LOGIN);

    }

    private void attachCloseListener() {
        getStage().setOnCloseRequest(event -> {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Quit application");
            try {
                dataToMainConcrete.logout();


            } catch (NullPointerException e) {
                //user is not connected so nullpointer exception is raised
            }
        });
    }

    /**
     * Change the current module (Main of Game)
     *
     * @param mode module wanted
     * @throws IOException
     */
    public void changeModule(ModuleMode mode) throws IOException {
        if (mode.equals(ModuleMode.MAIN_LOGIN)) {
            screenController = ihmMainScreenController;
            screenController.initIHM(null);
        } else if (mode.equals(ModuleMode.GAME_START)) {
            ihmGameScreenController = new IhmGameScreenController(this, dataToGameConcrete, comToGameConcrete,ihmGameToDataConcrete);
            screenController = ihmGameScreenController;
            screenController.initIHM(null);
        } else if (mode.equals(ModuleMode.GAME_END)) {
            screenController = ihmMainScreenController;
            screenController.initIHM(MenuController.class);
        }

    }

    /**
     * Get the current stage
     *
     * @return the stage
     */
    public Stage getStage() {
        return stage;
    }


}
