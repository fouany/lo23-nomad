package nomad.game.controller;

import javafx.scene.control.TextField;
import nomad.com.client.concrete.ComToIhmGameConcrete;
import nomad.common.data_structure.Message;
import nomad.common.data_structure.UserLight;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import nomad.common.ihm.IhmScreenController;
import nomad.data.client.DataToGameConcrete;
import nomad.game.IhmGameScreenController;

import java.sql.Timestamp;
import java.util.UUID;


public class ChatController extends GameComponentsAbstract{

    /**
     * Constructor that link the screen controller to the component controller
     *
     * @param screen
     */
    protected ChatController(IhmScreenController screen) {
        super(screen);
    }

    @FXML
    public TextArea chat;


    @FXML
    public TextField message;

    @Override
    public void init() {

    }

    @Override
    public void update(String type) {

    }

    public void OnClickSend() {
        String content = message.getText();
        if(content.isBlank()){
            IhmGameScreenController ihmGameScreenController = (IhmGameScreenController)super.screenControl;
            DataToGameConcrete dataInterface = ihmGameScreenController.getDataInterface();
            UserLight userLight = dataInterface.getUserLight();
            UUID idGame = dataInterface.getGame().getGameId();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Message message = new Message(userLight,idGame,content,timestamp);
            ComToIhmGameConcrete comInterface = ihmGameScreenController.getComInterface();
            System.out.println("ChatController | Send message");
            comInterface.transmissionCom(message);
        }
    }
}
