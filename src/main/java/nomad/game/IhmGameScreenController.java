package nomad.game;


import nomad.com.client.concrete.ComToIhmGameConcrete;
import nomad.common.data_structure.Game;
import nomad.common.ihm.IhmScreenController;
import nomad.common.MainApplication;
import nomad.data.client.DataToGameConcrete;
import nomad.game.controller.*;

import java.io.IOException;

/**
 * nomad.common.ihm.Main Main screen controller for the game module
 */
public class IhmGameScreenController extends IhmScreenController {

    protected DataToGameConcrete dataInterface;
    protected ComToIhmGameConcrete comInterface;

    /**
     * Contructor that set the module name and the default screen start
     * @param app
     * @param comInterface
     * @throws IOException
     */
    public IhmGameScreenController(MainApplication app, DataToGameConcrete dataInterface, ComToIhmGameConcrete comInterface) throws IOException {
        super(app);
        module = "GAME";
        defaultController = GameController.class;
        this.comInterface = comInterface;
        this.dataInterface = dataInterface;
        initGameControllers();
    }

    /**
     * Get the linked game
     * @return game
     */
    public Game getLinkedGame() {
        return dataInterface.getGame();
    }

    public void initGameControllers() throws IOException {
        ((GameController) controllerDict.get(defaultController)).instanciateControllers();
        initPanes();
        ((GameController) controllerDict.get(defaultController)).initControllers();
    }

    public DataToGameConcrete getDataInterface(){
        return dataInterface;
    }

    public ComToIhmGameConcrete getComInterface(){
        return comInterface;
    }

    @Override
    public void initStyles() {
    // TODO : implement initStyles method
    }

    @Override
    public void initPaths() {
        listPaths.add("fxml/page/game_view.fxml");
        listPaths.add("fxml/components/player_info.fxml");
        listPaths.add("fxml/components/board.fxml");
        listPaths.add("fxml/components/log.fxml");
        listPaths.add("fxml/components/skip_turn.fxml");
        listPaths.add("fxml/components/chat.fxml");
    }

    @Override
    public void initController() {
        controllerDict.put(GameController.class, new GameController(this));
        controllerDict.put(PlayerInfoController.class, new PlayerInfoController(this));
        controllerDict.put(BoardController.class, new BoardController(this));
        controllerDict.put(LogController.class, new LogController(this));
        controllerDict.put(SkipController.class, new SkipController(this));
        controllerDict.put(ChatController.class, new ChatController(this));
    }
}
