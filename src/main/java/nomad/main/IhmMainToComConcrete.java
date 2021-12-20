package nomad.main;

import nomad.common.data_structure.User;
import nomad.common.interfaces.main.IhmMainToComInterface;
import nomad.main.controller.SeeProfileController;

public class IhmMainToComConcrete implements IhmMainToComInterface {

    private IhmMainScreenController mainScreenController;

    public void setController(IhmMainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    @Override
    public void getUser(User user) {
        SeeProfileController.display(mainScreenController, user);
    }
}
