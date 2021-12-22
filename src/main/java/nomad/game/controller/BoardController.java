package nomad.game.controller;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import nomad.common.data_structure.*;
import nomad.common.ihm.IhmScreenController;
import nomad.game.IhmGameScreenController;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BoardController extends GameControllerAbstract {
    /**
     * Constructor that link the screen controller to the component controller
     *
     * @param screen
     */
    public BoardController(IhmScreenController screen) {
        super(screen);
    }

    @FXML
    private GridPane gameBoard;

    private boolean played = false;

    @FXML
    public void playMove(MouseEvent event) {
        Node source = (Node) event.getTarget();

        Game currentGame = getGameController().getCurrentGame();
        User currentUser = getGameController().getGameScreen().getDataInterface().getUser();
        Move move;
        if (currentGame.getCurrentPlayerUUID().equals(currentUser.getUserId())) {
            if (currentGame.getMoves().size() < currentGame.getNbOfTowers()) { // Place a tower
                move = new Tower(GridPane.getColumnIndex(source), GridPane.getRowIndex(source));
            } else { // Place a tile
                move = new Tile(GridPane.getColumnIndex(source), GridPane.getRowIndex(source), true);
            }
            move.setGameId(currentGame.getGameId());
            move.setUserId(currentGame.getCurrentPlayerUUID());
            ((IhmGameScreenController) screenControl).getComInterface().playMove(move);
            played = true;
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Not your turn!");
        }
    }

    @Override
    public void init() {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                Pane p = new Pane();
                gameBoard.add(p, i, j);
            }
        }
    }

    public void update() {
        played = false;
        ObservableList<Move> move = getGameController().getCurrentGame().getMoves();
        if (!move.isEmpty()) {
            Move newMove = move.get(move.size() - 1);
            ObservableList<Node> childrens = gameBoard.getChildren(); // Get all panes in board

            if (newMove instanceof Tower) {
                Tower tower = ((Tower) newMove);

                for (Node node : childrens) {
                    if (node.getClass() == Pane.class) {
                        if (GridPane.getRowIndex(node) == tower.getY() && GridPane.getColumnIndex(node) == tower.getX()) {
                            Platform.runLater(() -> {
                                ImageView towerView = new ImageView(getClass().getResource("img/tower.png").toExternalForm());
                                towerView.fitWidthProperty().bind(((Pane) node).widthProperty()); // Link width of image to pane width
                                towerView.fitHeightProperty().bind(((Pane) node).heightProperty()); // Link height of image to pane height
                                ((Pane) node).getChildren().add(towerView);
                            });

                            break;
                        }
                    }
                }
            } else if (newMove instanceof Tile) {
                Tile tile = ((Tile) newMove);
                for (Node node : childrens) {
                    if (node.getClass() == Pane.class) {
                        if (GridPane.getRowIndex(node) == tile.getY() && GridPane.getColumnIndex(node) == tile.getX()) {
                            Case boardCase = getGameController().getCurrentGame().getBoard().getCase(tile.getX(), tile.getY());

                            Platform.runLater(() -> {
                                StackPane stackPane = new StackPane();
                                ImageView tileView;

                                if (getGameController().getCurrentGame().getCurrentPlayerColor()) {
                                    tileView = new ImageView(getClass().getResource("img/redTile.png").toExternalForm());
                                } else {
                                    tileView = new ImageView(getClass().getResource("img/whiteTile.png").toExternalForm());
                                }

                                Text height = new Text(String.valueOf(boardCase.getHeight()));
                                stackPane.getChildren().add(tileView);
                                stackPane.getChildren().add(height);

                                ((Pane) node).getChildren().clear();
                                ((Pane) node).getChildren().add(stackPane);

                                tileView.fitWidthProperty().bind(((Pane) node).widthProperty()); // Link width of image to pane width
                                tileView.fitHeightProperty().bind(((Pane) node).heightProperty()); // Link height of image to pane height
                                ((Pane) node).getChildren().add(tileView);
                            });

                            break;
                        }
                    }
                }
            }
        }
    }
}
