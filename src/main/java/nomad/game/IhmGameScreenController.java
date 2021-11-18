package nomad.game;

import nomad.common.ihm.IhmScreenController;
import nomad.common.ihm.MainApplication;
import nomad.game.controller.IhmGameControllerScreen1;
import nomad.game.controller.IhmGameControllerScreen2;
import nomad.game.controller.IhmGameControllerScreen3;

import java.io.IOException;

/**
 * nomad.common.ihm.Main Main screen controller for the game module
 */
public class IhmGameScreenController extends IhmScreenController {

    /**
     * Contructor that set the module name and the default screen start
     * @param app
     * @throws IOException
     */
    public IhmGameScreenController(MainApplication app) throws IOException {
        super(app);
        module = "GAME";
        defaultStart = 0;
        initScenes();
    }

    @Override
    public void initStyles() {
    // TODO : implement initStyles method
    }

    @Override
    public void initPaths() {
        listPaths.add("fxml/ihm_game_screen_1.fxml");
        listPaths.add("fxml/imh_game_screen_2.fxml");
        listPaths.add("fxml/imh_game_screen_3.fxml");
    }

    @Override
    public void initController() {
        dictController.put(0, new IhmGameControllerScreen1(this));
        dictController.put(1, new IhmGameControllerScreen2(this));
        dictController.put(2, new IhmGameControllerScreen3(this));
    }
}
