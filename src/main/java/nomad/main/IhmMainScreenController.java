package nomad.main;
import nomad.common.ihm.IhmScreenController;
import nomad.common.ihm.MainApplication;
import nomad.main.resources.controller.ServerConnectionController;

import java.io.IOException;

public class IhmMainScreenController extends IhmScreenController {

    public IhmMainScreenController(MainApplication app) throws IOException {
        super(app);
        module="MAIN";
        defaultStart = 0;
        initScenes();
    }

    public void initPaths(){
        listPaths.add("src/main/java/nomad/main/resources/fxml/ihm_server_connection.fxml");
    }

    @Override
    public void initController() {
        dictController.put(0,new ServerConnectionController(this));
    }

    public void initStyles(){
        dictStyles.put(0, "src/main/java/nomad/main/resources/Poppins/style.css");
    }
}
