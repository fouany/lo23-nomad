package nomad.main;

import nomad.common.ihm.IhmScreenController;
import nomad.common.MainApplication;
import nomad.common.interfaces.data.DataToIhmMainInterface;
import nomad.main.controller.CreateGameController;
import nomad.main.controller.ServerConnectionController;
import nomad.main.controller.MenuController;
import nomad.main.controller.LoginController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class IhmMainScreenController extends IhmScreenController {

    private Map<String, String> attributes;
    private DataToIhmMainInterface dataI;
    private final String stylesheet = "Poppins/style.css";

    private MenuController menuController;

    public IhmMainScreenController(MainApplication app, DataToIhmMainInterface dataI) throws IOException {
        super(app);
        module = "MAIN";
        defaultStart = 3;
        attributes = new HashMap<>();
        initScenes();
        this.dataI = dataI;
    }

    public MenuController getMenuController() {
        return menuController;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public DataToIhmMainInterface getDataI() {
        return dataI;
    }

    public void initPaths() {
        listPaths.add("fxml/ihm_login_connection.fxml");
        listPaths.add("fxml/ihm_server_connection.fxml");
        listPaths.add("fxml/ihm_menu.fxml");
        listPaths.add("fxml/ihm_create_game.fxml");
    }

    @Override
    public void initController() {
        menuController = new MenuController(this);
        dictController.put(0, new LoginController(this));
        dictController.put(1, new ServerConnectionController(this));
        dictController.put(2, menuController);
    }

    public void initStyles() {
        dictStyles.put(0, stylesheet);
        dictStyles.put(2, stylesheet);
        dictStyles.put(1, stylesheet);
    }
}
