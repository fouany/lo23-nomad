package nomad.main;

import nomad.common.data_structure.Game;
import nomad.common.data_structure.Session;
import nomad.common.data_structure.User;
import nomad.common.interfaces.main.IhmMainToDataInterface;

public class IhmMainToDataConcrete implements IhmMainToDataInterface {

    private IhmMainScreenController screenController;

    public void setController(IhmMainScreenController screenController) {
        this.screenController = screenController;
    }

    public IhmMainToDataConcrete () {
    }

    @Override
    public void updateObservable(Session session) {
        //session.addObserver();
    }

    @Override
    public void updateObservable(Game game) {
      //todo fix Observer on Observable
    }

    @Override
    public void updateObservable(User user) {
      //todo fix Observer on Observable
    }
}
