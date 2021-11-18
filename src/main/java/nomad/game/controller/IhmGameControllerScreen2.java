package nomad.game.controller;

import nomad.common.ihm.IhmControllerComponent;
import nomad.common.ihm.IhmScreenController;

public class IhmGameControllerScreen2 extends IhmControllerComponent {
    public IhmGameControllerScreen2(IhmScreenController screen) {
        super(screen);
    }

    public void goToFirst() {
        screenControl.changeScreen(0);
    }
    public void goToThirdScreen() {
        screenControl.changeScreen(2);
    }

}


