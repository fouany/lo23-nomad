package nomad.main;

import nomad.common.ihm.IhmScreenController;
import nomad.common.MainApplication;
import nomad.common.interfaces.com.ComToIhmMainInterface;
import nomad.common.interfaces.data.DataToIhmMainInterface;
import nomad.main.controller.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class IhmMainScreenController extends IhmScreenController {

    private Map<String, String> attributes;
    private DataToIhmMainInterface dataI;
    private ComToIhmMainInterface comI;
    private final String stylesheet = "Poppins/style.css";

    private MenuController menuController;
    private CreateGameController createGameController;
    private ViewGameController viewGameController;


    public IhmMainScreenController(MainApplication app, DataToIhmMainInterface dataI) throws IOException {
        super(app);
        module = "MAIN";
        defaultStart = 3;
        attributes = new HashMap<>();
        initScenes();
        this.dataI = dataI;
        DialogController.initDialog(dictScenes.get(4));
        /**
         * todo ajouter l'interface com concrete
         * **/
    }

    public ComToIhmMainInterface getComI()
    {
        return comI;
    }

    public MenuController getMenuController() {
        return menuController;
    }


    public CreateGameController getCreateGameController(){return  createGameController;}

    public ViewGameController getViewGameController() {
        return viewGameController;
    }


    public Map<String, String> getAttributes() {
        return attributes;
    }



    public DataToIhmMainInterface getDataI() {
        return dataI;
    }


    public void initPaths(){
        listPaths.add("fxml/ihm_login_connection.fxml");
        listPaths.add("fxml/ihm_server_connection.fxml");
        listPaths.add("fxml/ihm_menu.fxml");
        listPaths.add("fxml/ihm_create_game.fxml");
        listPaths.add("fxml/ihm_dialog.fxml");
        listPaths.add("fxml/ihm_view_game.fxml");

    }

    @Override
    public void initController() {
        menuController = new MenuController(this);

        createGameController = new CreateGameController(this);
        viewGameController = new ViewGameController(this);
        dictController.put(0, new LoginController(this));
        dictController.put(1, new ServerConnectionController(this));
        dictController.put(2, menuController);
        dictController.put(3,createGameController);
        dictController.put(4, new DialogController(this));
        dictController.put(5, viewGameController);

    }

    public void initStyles() {
        dictStyles.put(0, stylesheet);
        dictStyles.put(1, stylesheet);
        dictStyles.put(2, stylesheet);
        dictStyles.put(3, stylesheet);
    }
}
