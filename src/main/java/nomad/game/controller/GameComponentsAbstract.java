package nomad.game.controller;

import nomad.common.data_structure.Game;
import nomad.common.ihm.IhmControllerComponent;
import nomad.common.ihm.IhmScreenController;

public abstract class GameComponentsAbstract extends IhmControllerComponent {

    private GameController gameController;

    /**
     * Constructor that link the screen controller to the component controller
     *
     * @param screen
     */
    protected GameComponentsAbstract(IhmScreenController screen) {
        super(screen);
    }

    /**
     * Init the controlleur
     */
    public abstract void init(Game game);

    /**
     * Update the component
     */
    public abstract void update();

    /**
     * Set the parent controller
     * @param gameController
     */
    protected void setParentController(GameController gameController){
        this.gameController = gameController;
    }

    /**
     * Get the game controller
     * @return
     */
    public GameController getGameController() {
        return gameController;
    }

}
