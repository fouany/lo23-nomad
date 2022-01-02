// Internal imports
package nomad.common.ihm;

// JavaFX imports

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import nomad.common.MainApplication;


import java.io.IOException;
import java.net.URL;
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
    protected Class<?> defaultController;
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
    protected HashMap<Class<?>, String> styleDict = new HashMap<>();

    /**
     * Dict of all the controllers of the current module
     */
    protected HashMap<Class<?>, IhmControllerComponent> controllerDict = new HashMap<>();

    /**
     * Dict of all the panes of the current module
     */
    protected HashMap<Class<?>, Pane> paneDict = new HashMap<>();

    /**
     * Dict of non-pane elements of the module
     */
    protected HashMap<Class<?>, Node> nodeDict = new HashMap<>();

    /**
     * Callback to return controllers from the dictionnary
     */
    private final Callback<Class<?>, Object> controllerFactory = controllerDict::get;

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
    public void initIHM(Class<?> controllerClass) {
        if (controllerClass == null) {
            controllerClass = defaultController;
        }

        Class<?> finalControllerClass = controllerClass;
        Platform.runLater(() -> {
            mainApp.getStage().setTitle("Nomad - " + module);
            if (mainApp.getStage().getScene() == null) { // No scene, initialize it with correct root
                mainApp.getStage().setScene(new Scene(paneDict.get(finalControllerClass)));
            } else { // Change root node of scene
                mainApp.getStage().getScene().setRoot(paneDict.get(finalControllerClass));
            }
            mainApp.getStage().show();
        });
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
     * Init all the panes in the panes dict
     *
     * @throws IOException When loading a specified FXML fails
     */
    protected void initPanes() throws IOException {
        for (String path : listPaths) {
            URL fxmlUrl = this.getClass().getResource(path);
            if (fxmlUrl == null) {
                return;
            }

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(controllerFactory);
            fxmlLoader.setLocation(fxmlUrl);

            Pane pane;
            Node node = fxmlLoader.load();
            try {
                pane = (Pane) node; // node might not be a Pane
            } catch (ClassCastException e) { // Not a Pane
                nodeDict.put(fxmlLoader.getController().getClass(), node);
                return;
            }

            paneDict.put(fxmlLoader.getController().getClass(), pane); // Is a Pane
            if (styleDict.containsKey(fxmlLoader.getController().getClass())) {
                pane.getStylesheets().add(getFxmlUrl(styleDict.get(fxmlLoader.getController().getClass())));
            }
        }
    }
    /**
     * Change the current screen (with a scene of the current module)
     *
     * @param controllerClass The class of the controller associated to the FXML to recover the style for
     */
    public void changeScreen(Class<?> controllerClass) {
        mainApp.getStage().getScene().setRoot(paneDict.get(controllerClass));
        mainApp.getStage().show();
    }

    public Stage getStage() {
        return mainApp.getStage();
    }

    public IhmControllerComponent getController(Class<?> controllerClass) {
        return controllerDict.get(controllerClass);
    }

    /**
     * Change the current active module
     */
    public void changeModule(ModuleMode mode) throws IOException {
        mainApp.changeModule(mode);
    }
}
