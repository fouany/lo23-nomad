package nomad.game.controller;

import nomad.common.ihm.IhmControllerComponent;
import nomad.common.ihm.IhmScreenController;

import java.io.IOException;

public class IhmGameControllerScreen1 extends IhmControllerComponent {
    public IhmGameControllerScreen1(IhmScreenController screen) {
        super(screen);
    }

    public void backMenu() throws IOException {
        screenControl.changeModule();
    }

    public void goToSecondScreen() {
        screenControl.changeScreen(1);
    }
}

//Bonjour