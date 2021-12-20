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
    private ModifyProfileController profileController;

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }


    public IhmMainScreenController(MainApplication app, DataToIhmMainInterface dataI, ComToIhmMainInterface comI) throws IOException {
        super(app);
        module = "MAIN";
        defaultController = LoginController.class;
        attributes = new HashMap<>();
        initPanes();
        this.dataI = dataI;
        this.comI = comI;

        DialogController.initDialog(paneDict.get(DialogController.class));
        SeeProfileController.initDialog(paneDict.get(SeeProfileController.class));
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
    public ModifyProfileController getProfileController(){return profileController;}
    public void initPaths() {
        listPaths.add("fxml/ihm_login_connection.fxml");
        listPaths.add("fxml/ihm_server_connection.fxml");
        listPaths.add("fxml/ihm_menu.fxml");
        listPaths.add("fxml/ihm_create_game.fxml");
        listPaths.add("fxml/ihm_dialog.fxml");
        listPaths.add("fxml/ihm_view_game.fxml");
        listPaths.add("fxml/ihm_waiting_room.fxml");
        listPaths.add("fxml/ihm_modify_profile.fxml");
        listPaths.add("fxml/ihm_see_profile.fxml");

    }

    @Override
    public void initController() {
        menuController = new MenuController(this);
        createGameController = new CreateGameController(this);
        viewGameController = new ViewGameController(this);
        waitingRoomController = new WaitingRoomController(this);
        profileController = new ModifyProfileController(this);
        controllerDict.put(LoginController.class, new LoginController(this));
        controllerDict.put(ServerConnectionController.class, new ServerConnectionController(this));
        controllerDict.put(MenuController.class, menuController);
        controllerDict.put(CreateGameController.class, createGameController);
        controllerDict.put(DialogController.class, new DialogController(this));
        controllerDict.put(ViewGameController.class, viewGameController);
        controllerDict.put(WaitingRoomController.class, waitingRoomController);
        controllerDict.put(ModifyProfileController.class, profileController);
        controllerDict.put(SeeProfileController.class, new SeeProfileController(this));
    }

    public void initStyles() {
        String stylesheet = "Poppins/style.css";
        styleDict.put(LoginController.class, stylesheet);
        styleDict.put(ServerConnectionController.class, stylesheet);
        styleDict.put(MenuController.class, stylesheet);
        styleDict.put(CreateGameController.class, stylesheet);
    }
}
