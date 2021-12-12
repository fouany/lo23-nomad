package nomad.main;

import javafx.application.Platform;
import nomad.common.data_structure.Game;
import nomad.common.data_structure.GameException;
import nomad.common.data_structure.Session;
import nomad.common.data_structure.User;
import nomad.common.interfaces.main.IhmMainToDataInterface;

import java.util.logging.Level;
import java.util.logging.Logger;

public class IhmMainToDataConcrete implements IhmMainToDataInterface {

    private IhmMainScreenController mainScreenController;

    public void setController(IhmMainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    public IhmMainToDataConcrete() {
        // TODO : implement constructor
    }

    /**
     * Listeners are added on getGamesInPlay() and getGamesInLobby()
     * in order to track all changes in both these lists of games.
     * The parameters given to these functions are ListChangeListener
     * both defined in the Controller class : ViewGameController.
     */
    @Override
    public void updateObservable(Session session) {
        session.getConnectedUsers().addListener(mainScreenController.getMenuController());
        session.getGamesInPlay().addListener(mainScreenController.getViewGameController().gamesAsViewer);
        session.getGamesInLobby().addListener(mainScreenController.getViewGameController().gamesAsPlayer);
        mainScreenController.setSession(session);
    }

    /*todo add custom method newPLayerAskingToJoin*/

    @Override
    public void updateObservable(Game game) {
        Logger.getAnonymousLogger().log(Level.INFO, "Call");
        if (!game.isGameLaunched() && game.getOpponent() == null) //game just has been created
        {
            mainScreenController.getCreateGameController().displayWaitingRoom(game);
            try {
                mainScreenController.getWaitingRoomController().gameUpdate(game);
            } catch (GameException e) {
                /*todo handle game exception*/
            }
            game.addObserver(mainScreenController.getWaitingRoomController());
        } else if (!game.isGameLaunched() && game.getOpponent() != null) {
            mainScreenController.getCreateGameController().displayWaitingRoom(game);
            Platform.runLater(() -> {
                try {
                    mainScreenController.getWaitingRoomController().gameUpdate(game);
                } catch (GameException e) {
                    e.printStackTrace();
                }
            });

            Logger.getAnonymousLogger().log(Level.INFO, "Call 2 ");
        }
    }

    @Override
    public void updateGameCreated(Game game) {

        mainScreenController.getCreateGameController().displayWaitingRoom(game);
        try {
            mainScreenController.getWaitingRoomController().gameUpdate(game);
        } catch (GameException e) {
            /*todo handle game exception*/
        }
        game.addObserver(mainScreenController.getWaitingRoomController());


    }

    @Override
    public void updateAcceptOpponent(Game game) {
       /* if (mainScreenController.getDataI().getUser().getUserId().equals(game.getOpponent().getId())) {
            mainScreenController.getCreateGameController().displayWaitingRoom(game);
        }*/
        if(mainScreenController.getDataI().getUser().getUserId().equals(game.getOpponent().getId())) {
            Platform.runLater(() ->
            {
                mainScreenController.getViewGameController().acceptedInGame();
                game.addObserver(mainScreenController.getWaitingRoomController());
            });
        }

        Platform.runLater(() -> {
            try {
                mainScreenController.getWaitingRoomController().gameUpdate(game);
            } catch (GameException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void updateObservable(User user) {
        // TODO : fix Observer on Observable
    }
}
