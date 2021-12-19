package nomad.game.controller;


import nomad.common.data_structure.Game;
import nomad.common.data_structure.Skip;
import nomad.common.ihm.IhmScreenController;

public class SkipController extends GameControllerAbstract {

    /**
     * Constructor that link the screen controller to the component controller
     *
     * @param screen
     */
    public SkipController(IhmScreenController screen) {
        super(screen) ;
    }

    @Override
    public void init() {}

    @Override
    public void update(String type) {}

    private void skipMove() {
        GameController gameController = getGameController() ;
        Game currentGame = gameController.getCurrentGame() ;
        Skip skip = new Skip(true) ;
        currentGame.getMoves().add(skip) ;
    }

}
