package nomad.game.controller;

import nomad.common.ihm.IhmControllerComponent;
import nomad.common.ihm.IhmScreenController;

public class GameController extends IhmControllerComponent {
    /**
     * Constructor that link the screen controller to the component controller
     *
     * @param screen
     */
    public GameController(IhmScreenController screen) {
        super(screen);
    }
}
