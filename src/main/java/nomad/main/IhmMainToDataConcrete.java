package nomad.main;

import nomad.common.data_structure.Game;
import nomad.common.data_structure.Session;
import nomad.common.data_structure.User;
import nomad.common.interfaces.main.IhmMainToDataInterface;

public class IhmMainToDataConcrete implements IhmMainToDataInterface {

    private IhmMainScreenController mainScreenController;

    public void setController(IhmMainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    public IhmMainToDataConcrete () {
        // TODO : implement constructor
    }

    @Override
    public void updateObservable(Session session) {
        session.getConnectedUsers().addListener(mainScreenController.getMenuController());
    }

    @Override
    public void updateObservable(Game game) {
      // TODO : fix Observer on Observable
    }

    @Override
    public void updateObservable(User user) {
      // TODO : fix Observer on Observable
    }
}
