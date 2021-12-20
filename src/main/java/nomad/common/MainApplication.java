package nomad.common;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import nomad.com.client.ClientController;
import nomad.com.client.concrete.ComClientToDataConcrete;
import nomad.com.client.concrete.ComClientToIhmMainConcrete;
import nomad.com.client.concrete.ComToIhmGameConcrete;
import nomad.common.data_structure.UserLight;
import nomad.common.ihm.IhmScreenController;
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

    private IhmGameToDataInterface ihmGameToDataConcrete;

    public MainApplication() throws IOException {
        initConcreteInterface();
        initController();
        linkConcreteController();
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


        comClientToIhmMainConcrete.setClientController(clientController);
        ihmMainToDataConcrete.setController(ihmMainScreenController);
        comClientToDataConcrete.setController(clientController);
    }

    private final int MIN_WIDTH = 935;
    private final int MIN_HEIGHT = 610;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        attachCloseListener();
        stage.setMinHeight(MIN_HEIGHT);
        stage.setMinWidth(MIN_WIDTH);
        this.changeModule("MAIN");

    }


    private void attachCloseListener()
    {
        getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Logger.getAnonymousLogger().log(Level.SEVERE, "Quit application");
                try {
                    dataToMainConcrete.logout();


                }
                catch (NullPointerException e)
                {
                    //user is not connected so nullpointer exception is raised
                }
            }
        });
    }
    /**
     * Change the current module (Main of Game)
     *
     * @param mode module wanted
     * @throws IOException
     */
    public void changeModule(String mode) throws IOException {
        if (mode.equals("MAIN")) {
            screenController = ihmMainScreenController;
        } else {
            ihmGameScreenController = new IhmGameScreenController(this, dataToGameConcrete, comToGameConcrete);
            screenController = ihmGameScreenController;
        }
        screenController.initIHM();
    }

    /**
     * Get the current stage
     *
     * @return the stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Entry point of the application
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }


}
