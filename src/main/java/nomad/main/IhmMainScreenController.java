package nomad.main;

import nomad.common.data_structure.Session;
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

    private MenuController menuController;
    private CreateGameController createGameController;
    private ViewGameController viewGameController;
    private WaitingRoomController waitingRoomController;
    private Session session;

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public IhmMainScreenController(MainApplication app, DataToIhmMainInterface dataI, ComToIhmMainInterface comI) throws IOException {
        super(app);
        module = "MAIN";
        defaultStart = ControllerIndex.LOGIN.index;
        attributes = new HashMap<>();
        initPanes();
        this.dataI = dataI;
        this.comI = comI;
        DialogController.initDialog(paneDict.get(4));
        // TODO : ajouter l'interface com concrete
    }

    public ComToIhmMainInterface getComI() {
        return comI;
    }

    public MenuController getMenuController() {
        return menuController;
    }

    public WaitingRoomController getWaitingRoomController() {
        return waitingRoomController;
    }

    public CreateGameController getCreateGameController() {
        return createGameController;
    }

    public ViewGameController getViewGameController() {
        return viewGameController;
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
        listPaths.add("fxml/ihm_dialog.fxml");
        listPaths.add("fxml/ihm_view_game.fxml");
        listPaths.add("fxml/ihm_waiting_room.fxml");
    }

    @Override
    public void initController() {
        menuController = new MenuController(this);
        createGameController = new CreateGameController(this);
        viewGameController = new ViewGameController(this);
        waitingRoomController = new WaitingRoomController(this);

        controllerDict.put(ControllerIndex.LOGIN.index, new LoginController(this));
        controllerDict.put(ControllerIndex.SERVER_CONNECTION.index, new ServerConnectionController(this));
        controllerDict.put(ControllerIndex.MENU.index, menuController);
        controllerDict.put(ControllerIndex.CREATE_GAME.index, createGameController);
        controllerDict.put(ControllerIndex.DIALOG.index, new DialogController(this));
        controllerDict.put(ControllerIndex.VIEW_GAME.index, viewGameController);
        controllerDict.put(ControllerIndex.WAITING_ROOM.index, waitingRoomController);
    }

    public void initStyles() {
        String stylesheet = "Poppins/style.css";
        styleDict.put(0, stylesheet);
        styleDict.put(1, stylesheet);
        styleDict.put(2, stylesheet);
        styleDict.put(3, stylesheet);
    }
}
