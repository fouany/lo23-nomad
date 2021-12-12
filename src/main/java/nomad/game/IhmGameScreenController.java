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

    private DataToGameConcrete dataInterface;
    private ComToIhmGameConcrete comInterface;

    /**
     * Contructor that set the module name and the default screen start
     * @param app
     * @param comInterface
     * @throws IOException
     */
    public IhmGameScreenController(MainApplication app, DataToGameConcrete dataInterface, ComToIhmGameConcrete comInterface) throws IOException {
        super(app);
        module = "GAME";
        defaultStart = ControllerIndex.GAME.index;
        this.comInterface = comInterface;
        this.dataInterface = dataInterface;
        initPanes();
        initListenerGame();
    }

    /**
     * Get the linked game
     * @return game
     */
    public Game getLinkedGame() {
        return dataInterface.getGame();
    }

    public void initListenerGame(){
        ((GameController) controllerDict.get(0)).initListener();
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
        controllerDict.put(ControllerIndex.GAME.index, new GameController(this));
        controllerDict.put(ControllerIndex.PLAYER_INFO.index, new PlayerInfoController(this));
        controllerDict.put(ControllerIndex.BOARD.index, new BoardController(this));
        controllerDict.put(ControllerIndex.LOG.index, new LogController(this));
        controllerDict.put(ControllerIndex.SKIP.index, new SkipController(this));
        controllerDict.put(ControllerIndex.CHAT.index, new ChatController(this));
    }
}
