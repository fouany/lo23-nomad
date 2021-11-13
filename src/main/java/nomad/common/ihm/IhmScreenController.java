// Internal imports
package nomad.common.ihm;

// JavaFX imports
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;

// Utils
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
    protected HashMap<Integer,Scene> dictScenes = new HashMap<>();

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
     * @param app main application
     */
    protected IhmScreenController(MainApplication app){
        mainApp = app;
        initController();
        initPaths();
        initStyles();
    }

    /**
     * Call when we initialize the current module
     */
    public void initIHM(){
        mainApp.getStage().setTitle("Nomad - Game");
        mainApp.getStage().setScene(dictScenes.get(defaultStart));
        mainApp.getStage().show();
    }

    /**
     * Use to get the url of a fxml file
     * @param path path the file
     * @return the URL
     * @throws MalformedURLException
     */
    protected URL getFxmlUrl(String path) throws MalformedURLException {
        return new File(path).toURI().toURL();
    }

    /**
     * Init all the scenes in the scene dict
     * @throws IOException
     */
    protected void initScenes() throws IOException {
        for (int i = 0; i < listPaths.size() ; i++){
            FXMLLoader fxmlLoader = loadFile(listPaths.get(i),dictController.get(i));
            Scene scene = new Scene(fxmlLoader.load());
            if (dictStyles.containsKey(i)){
                scene.getStylesheets().add(getFxmlUrl(dictStyles.get(i)).toExternalForm());
            }
            dictScenes.put(i,scene);
        }
    }

    /**
     * Load a scene with the linked controller
     * @param url url of the scene
     * @param interfaceController controller to linked
     * @return
     */
    public FXMLLoader loadFile(String url, IhmControllerComponent interfaceController){
        try {
            return new FXMLLoader(
                    new File(url).toURI().toURL(),
                    null,
                    new JavaFXBuilderFactory(),
                    param -> interfaceController
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Change the current screen (with a scene of the current module)
     * @param i
     */
    public void changeScreen(int i){
        mainApp.getStage().setScene(dictScenes.get(i));
        mainApp.getStage().show();
    }

    /**
     * Change the current module
     * @throws IOException
     */
    public void changeModule() throws IOException {
        if (module.equals("MAIN")) {
            mainApp.changeModule("GAME");
        } else {
            mainApp.changeModule("MAIN");
        }
    }
}
