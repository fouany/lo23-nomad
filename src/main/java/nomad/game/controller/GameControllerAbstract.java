package nomad.game.controller;

import nomad.common.ihm.IhmControllerComponent;
import nomad.common.ihm.IhmScreenController;

public abstract class GameControllerAbstract extends IhmControllerComponent {

    private GameController gameController;

    /**
     * Constructor that link the screen controller to the component controller
     *
     * @param screen
     */
    protected GameControllerAbstract(IhmScreenController screen) {
        super(screen);
    }

    /**
     * Init the controlleur
     */
    public abstract void init();

    /**
     * Update the component
     * @param type type of update
     */
    public abstract void update(String type);

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
