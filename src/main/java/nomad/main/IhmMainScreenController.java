package nomad.main;

import nomad.common.ihm.IhmScreenController;
import nomad.common.ihm.MainApplication;
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

    public IhmMainScreenController(MainApplication app, DataToIhmMainInterface dataI) throws IOException {
        super(app);
        module = "MAIN";
        defaultStart = 3;
        attributes = new HashMap<>();
        initScenes();
        this.dataI = dataI;
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
        dictController.put(0, new LoginController(this));
        dictController.put(1, new ServerConnectionController(this));
        dictController.put(2, new MenuController(this));
        dictController.put(3, new CreateGameController(this));

    }

    public void initStyles() {
        dictStyles.put(0, "Poppins/style.css");
        dictStyles.put(1, "Poppins/style.css");
        dictStyles.put(2, "Poppins/style.css");
        dictStyles.put(3, "Poppins/style.css");
    }
}
