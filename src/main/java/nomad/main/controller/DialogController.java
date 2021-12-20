package nomad.main.controller;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import nomad.common.ihm.IhmControllerComponent;
import nomad.common.ihm.IhmScreenController;
import nomad.main.IhmMainScreenController;

import java.util.concurrent.atomic.AtomicReference;

public class DialogController extends IhmControllerComponent {


    /**
     * Constructor that link the screen controller to the component controller
     *
     * @param screen
     */
    public DialogController(IhmMainScreenController screen) {
        super(screen);

    }

    enum DialogStatus {
        SUCCESS,
        WARNING,
        ERROR,
        QUESTION
}
    public static void centerDialog(Stage stage, Stage primaryStage) {
        // Calculate the center position of the parent Stage
        double centerXPosition = primaryStage.getX() + primaryStage.getWidth()/2d;
        double centerYPosition = primaryStage.getY() + primaryStage.getHeight()/2d;

        // Hide the pop-up stage before it is shown and becomes relocated
        stage.setOnShowing(ev -> stage.hide());

        // Relocate the pop-up Stage
        stage.setOnShown(ev -> {
            stage.setX(centerXPosition - stage.getWidth()/2d);
            stage.setY(centerYPosition - stage.getHeight()/2d - 50);
            stage.show();
        });
    }

    private static Pane dialogPane = null;
    private static Scene scene = null;

    
public static Boolean display(String title, String content, DialogStatus status , IhmScreenController controller) {
    Stage stage = new Stage();
    Stage primaryStage = controller.getStage();
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.initStyle(StageStyle.UNDECORATED);
    if(DialogController.scene == null)
    {
        DialogController.scene = new Scene(DialogController.dialogPane);
    }
    stage.setScene(DialogController.scene);
    stage.initOwner(primaryStage);
    
    DialogController.centerDialog(stage, primaryStage);

    AtomicReference<Boolean> accept = new AtomicReference<>(false);
    Button yes = (Button)dialogPane.lookup("#oui");
    Button no = (Button)dialogPane.lookup("#non");
    Button button = (Button) dialogPane.lookup("#dimiss");
    if(status == DialogStatus.QUESTION)
    {
        button.setVisible(false);
        button.setManaged(false);
        yes.setManaged(true);
        yes.setVisible(true);
        no.setManaged(true);
        no.setVisible(true);
        yes.setOnAction(e -> {
            accept.set(true);
            stage.close();
        });
        no.setOnAction(e -> {
            accept.set(false);
            stage.close();
        });
    }
    else {
        button.setVisible(true);
        button.setManaged(true);
        yes.setManaged(false);
        yes.setVisible(false);
        no.setManaged(false);
        no.setVisible(false);
        button.setOnAction(e -> stage.close());
    }

    Label titleL = (Label) dialogPane.lookup("#title");
    titleL.setText(title);
    Label contentL = (Label) dialogPane.lookup("#content");
    contentL.setText(content);

    VBox container = (VBox) dialogPane.lookup("#container");

    switch (status)
    {
        case ERROR:
            container.getStyleClass().set(2, "dialog-error");
            break;

        case SUCCESS:
            container.getStyleClass().set(2, "dialog-success");
            break;

        case WARNING:
            container.getStyleClass().set(2, "dialog-warning");
            break;

        default:
            /*todo throw exception*/
    }

    stage.showAndWait();
    return accept.get();

}

public static void initDialog(Pane pane)
{
    DialogController.dialogPane = pane;
}
}
