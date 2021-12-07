// Internal imports
package nomad.common.ihm;

// JavaFX imports

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import nomad.common.MainApplication;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Parent class of any screen controller of a module
 */
public abstract class IhmScreenController {
    /**
     * Default screen that will be display when the class is initialized
     */
    protected int defaultStart;
    /**
     * nomad.common.ihm.Main app of the GUI
     */
    protected MainApplication mainApp;

    /**
     * Name of the module (GAME or MAIN)
     */
    protected String module;

    /**
     * List of all the path of the views
     */
    protected List<String> listPaths = new ArrayList<>();

    /**
     * Dict of all the style of the current module
     */
    protected HashMap<Integer, String> dictStyles = new HashMap<>();

    /**
     * Dict of all the controllers of the current module
     */
    protected HashMap<Integer, IhmControllerComponent> dictController = new HashMap<>();

    /**
     * Dict of all the scenes of the current module
     */
    protected HashMap<Integer, Pane> dictScenes = new HashMap<>();

    /**
     * Init all styles for the current module
     */
    public abstract void initStyles();

    /**
     * Init all paths for the current module
     */
    public abstract void initPaths();

    /**
     * Init all controllers for the current module
     */
    public abstract void initController();

    /**
     * Main constructor that initialize all controllers / paths and styles of the module
     *
     * @param app main application
     */
    protected IhmScreenController(MainApplication app) {
        mainApp = app;
        initController();
        initPaths();
        initStyles();
    }

    /**
     * Initialize a module and change the root node in scene
     */
    public void initIHM() {
        mainApp.getStage().setTitle("Nomad - " + module);
        if (mainApp.getStage().getScene() == null) { // No scene, initialize it with correct root
            mainApp.getStage().setScene(new Scene(dictScenes.get(defaultStart)));
        } else { // Change root node of scene
            mainApp.getStage().getScene().setRoot(dictScenes.get(defaultStart));
        }
        mainApp.getStage().show();
    }

    /**
     * Use to get the url of a fxml file
     *
     * @param path path the file
     * @return the URL
     * @throws NullPointerException on unexisting path
     */
    protected String getFxmlUrl(String path) throws NullPointerException {
        return this.getClass().getResource(path).toExternalForm();
    }

    /**
     * Init all the scenes in the scene dict
     *
     * @throws IOException
     */
    protected void initScenes() throws IOException {
        for (int i = 0; i < listPaths.size(); i++) {

            FXMLLoader fxmlLoader = loadFile(listPaths.get(i), dictController.get(i));
            Pane pane = fxmlLoader.load();
            if (dictStyles.containsKey(i)) {
                pane.getStylesheets().add(getFxmlUrl(dictStyles.get(i)));
            }
            dictScenes.put(i, pane);
        }
    }

    /**
     * Load a scene with the linked controller
     *
     * @param url                 url of the scene
     * @param interfaceController controller to linked
     * @return
     */
    public FXMLLoader loadFile(String url, IhmControllerComponent interfaceController) {
        try {
            return new FXMLLoader(
                    this.getClass().getResource(url),
                    null,
                    new JavaFXBuilderFactory(),
                    param -> interfaceController
            );
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Change the current screen (with a scene of the current module)
     *
     * @param i
     */
    public void changeScreen(int i) {
        mainApp.getStage().getScene().setRoot(dictScenes.get(i));
        //mainApp.getStage().getScene().getWindow().setWidth( mainApp.getStage().getScene().getWindow().getWidth() + 0.001);
        //mainApp.getStage().getScene().getWindow().setWidth( mainApp.getStage().getScene().getWindow().getWidth() - 0.001);
        mainApp.getStage().show();
    }

    public Stage getStage() {
        return mainApp.getStage();
    }

    /**
     * Change the current active module
     */
    public void changeModule() throws IOException {
        if (module.equals("MAIN")) {
            mainApp.changeModule("GAME");
        } else {
            mainApp.changeModule("MAIN");
        }
    }
}
