package nomad.common;

import nomad.com.server.ComToDataServerConcrete;
import nomad.com.server.ServerController;
import nomad.data.server.DataServerController;
import nomad.data.server.DataToComConcrete;
import nomad.data.server.GamesController;
import nomad.data.server.UserController;

public class ServerApplication {
    public static void main(String[] args) {
        UserController userController = new UserController();
        GamesController gamesController = new GamesController();
        ComToDataServerConcrete comToData = new ComToDataServerConcrete();
        DataServerController dataServerController = new DataServerController(
                userController,
                gamesController,
                comToData
                );

        DataToComConcrete dataToCom = new DataToComConcrete(dataServerController);
        ServerController nomadServerController = new ServerController(12, dataToCom);
        nomadServerController.start();
    }
}
