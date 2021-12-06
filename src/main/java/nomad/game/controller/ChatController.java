package nomad.game.controller;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import nomad.com.client.concrete.ComToIhmGameConcrete;
import nomad.common.data_structure.Game;
import nomad.common.data_structure.Message;
import nomad.common.data_structure.UserLight;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import nomad.common.ihm.IhmScreenController;
import nomad.data.client.DataToGameConcrete;
import nomad.game.IhmGameScreenController;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;


public class ChatController extends GameComponentsAbstract{

    /**
     * Constructor that link the screen controller to the component controller
     *
     * @param screen
     */
    protected ChatController(IhmScreenController screen) {super(screen);}

    @FXML
    public TextArea chat;

    @FXML
    public TextField message;

    @FXML
    public ListView viewers;

    @Override
    public void init() {
        IhmGameScreenController ihmGameScreenController = (IhmGameScreenController)super.screenControl;
        DataToGameConcrete dataInterface = ihmGameScreenController.getDataInterface();
        Game game = dataInterface.getGame();
        ObservableList<UserLight> viewerList = game.getSpect();
        ListView<UserLight> listView = new ListView<UserLight>(viewerList);
    }

    @Override
    public void update(String type, Object value){
        if(type=="Message"){
            Message message = (Message)value;
            updateChat(message);
        }else if(type=="UserLight"){
            UserLight user = (UserLight) value;
            updateUserList(user);
        }
    }

    public void updateUserList(UserLight user){
        String login = user.getLogin();
        viewers.getItems().add(login);
    }

    public void updateChat(Message message) {
        //Message message = messages.get(messages.size()-1);
        UserLight sender = message.getSender();
        String senderLogin = sender.getLogin();
        Timestamp time = message.getTime();
        String content = message.getContent();
        String newMessage = senderLogin+"    "+content+"    "+time+"\n";
        chat.appendText(newMessage);
    }

    public void OnClickSend() {
        String content = message.getText();
        if(!content.isBlank()){
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

    public void handleListViewClick(){

    }

}
