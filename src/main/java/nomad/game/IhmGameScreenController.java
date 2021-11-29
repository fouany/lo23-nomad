package nomad.game;

import nomad.common.data_structure.Game;
import nomad.common.ihm.IhmScreenController;
import nomad.common.MainApplication;
import nomad.data.client.DataToGameConcrete;
import nomad.game.controller.GameController;

import java.io.IOException;

/**
 * nomad.common.ihm.Main Main screen controller for the game module
 */
public class IhmGameScreenController extends IhmScreenController {

    private DataToGameConcrete dataInterface;

    /**
     * Contructor that set the module name and the default screen start
     * @param app
     * @throws IOException
     */
    public IhmGameScreenController(MainApplication app, DataToGameConcrete dataInterface) throws IOException {
        super(app);
        module = "GAME";
        defaultStart = 0;
        this.dataInterface = dataInterface;
        initScenes();
    }

    /**
     * Get the linked game
     * @return game
     */
    public Game getLinkedGame() {
        return dataInterface.getGame();
    }

    @Override
    public void initStyles() {
    // TODO : implement initStyles method
    }

    @Override
    public void initPaths() {
        listPaths.add("fxml/page/main_game_view.fxml");
    }

    @Override
    public void initController() {
        dictController.put(0,new GameController(this));
    }
}
