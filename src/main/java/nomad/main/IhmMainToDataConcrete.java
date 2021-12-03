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
    /**
     * Listeners are added on getGamesInPlay() and getGamesInLobby()
     * in order to track all changes in both these lists of games.
     * The parameters given to these functions are ListChangeListener
     * both defined in the Controller class : ViewGameController.
     * */
    @Override
    public void updateObservable(Session session) {
        session.getConnectedUsers().addListener(mainScreenController.getMenuController());
        session.getGamesInPlay().addListener(mainScreenController.getViewGameController().gamesAsViewer);
        session.getGamesInLobby().addListener(mainScreenController.getViewGameController().gamesAsPlayer);

    }

    @Override
    public void updateObservable(Game game) {
        if(!game.isGameLaunched() && game.getOpponent() == null) //game just has been created
        {
            System.out.println("Bonjour");
            mainScreenController.getCreateGameController().displayWaitingRoom();
            game.addObserver(mainScreenController.getWaitingRoomController());
        }
    }

    @Override
    public void updateObservable(User user) {
      // TODO : fix Observer on Observable
    }
}
