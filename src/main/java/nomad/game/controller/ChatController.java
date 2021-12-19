package nomad.game.controller;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import nomad.com.client.concrete.ComToIhmGameConcrete;
import nomad.common.data_structure.*;
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
        viewers = new ListView<UserLight>(viewerList);
        //TODO : only display viewer logins
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
        viewers.getItems().add(user);
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
        IhmGameScreenController ihmGameScreenController = (IhmGameScreenController)super.screenControl;
        UserLight userClicked = (UserLight)viewers.getSelectionModel().getSelectedItem();
        //TODO : il nous manque une fonction get User from UserId
        //User user = userClicked.getUser(userClicked.getId());
        //InfoViewerController.display(user.getLogin(), user.getUserInfo().getProfilePicture(), user.getProfileStat(), IhmScreenController controller)

        /*
        DataToGameConcrete dataInterface = ihmGameScreenController.getDataInterface();
        User user = dataInterface.getUser();
        String profilePicture = user.getProfilePicture();
        ProfileStat profileStats = user.getProfileStat();
        String login = user.getLogin();
        */
    }

}
