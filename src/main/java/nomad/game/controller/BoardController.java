package nomad.game.controller;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
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
     * @param screen Parent screen controller
     */
    public BoardController(IhmScreenController screen) {
        super(screen);
    }

    @FXML
    private GridPane gameBoard;

    private static final String RED_TILE_IMG = "img/redTile.png";
    private static final String WHITE_TILE_IMG = "img/whiteTile.png";
    private static final String TOWER_IMG = "img/tower.png";

    @FXML
    public void playMove(MouseEvent event) {
        Node source = (Node) event.getTarget();

        if (Text.class.isAssignableFrom(source.getClass())) {
            source = source.getParent(); // LabeledText -> Label
        }

        // Source is an ImageView from a tile or a Label from the tile height
        if (source.getClass() == ImageView.class || source.getClass() == Label.class) {
            source = source.getParent(); // ImageView || Label -> StackPane
        }

        if (source.getClass() == StackPane.class) {
            source = source.getParent(); // StackPane -> Pane
        }

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

    /**
     * Add a tower to the game board.
     *
     * @param pane Pane containing the tower. If the pane is null, the method does nothing.
     */
    public void addTower(Pane pane) {
        if (pane != null) {
            Platform.runLater(() -> {
                ImageView towerView = new ImageView(getClass().getResource(TOWER_IMG).toExternalForm());
                towerView.fitWidthProperty().bind(pane.widthProperty()); // Link width of image to pane width
                towerView.fitHeightProperty().bind(pane.heightProperty()); // Link height of image to pane height
                pane.getChildren().add(towerView);
            });
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Could not add Tower to the gameboard !");
        }
    }

    /**
     * Redirect event catched by a stackpane to playmove function
     * @param event
     */
    public void handleStackpaneClick(MouseEvent event) {
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Reached handler !");
    }


    /**
     * Add a tile to the game board
     *
     * @param pane      Pane containing the tile. If the pane is null, the method does nothing
     * @param boardCase Case in the game board data structure. If the case is null, the method does nothing
     */
    public void addTile(Pane pane, Case boardCase) {
        if (pane != null && boardCase != null) {
            String tileImage;
            if (getGameController().getCurrentGame().getCurrentPlayerColor()) {
                tileImage = RED_TILE_IMG;
            } else {
                tileImage = WHITE_TILE_IMG;
            }

            Platform.runLater(() -> {
                StackPane stackPane = new StackPane();
                ImageView tileView = new ImageView(getClass().getResource(tileImage).toExternalForm());
                tileView.fitWidthProperty().bind(pane.widthProperty()); // Link width of image to pane width
                tileView.fitHeightProperty().bind(pane.heightProperty()); // Link height of image to pane height
                stackPane.getChildren().add(tileView);

                Label height = new Label(String.valueOf(boardCase.getHeight()));
                stackPane.getChildren().add(height);

                pane.getChildren().clear();
                pane.getChildren().add(stackPane);
            });
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Could not add Tile to the gameboard !");
        }
    }

    /**
     * Get the Pane element at the specified coordinates in the game board
     *
     * @param boardChildren List of all children of the gridpane
     * @param x             X coordinate of pane
     * @param y             Y coordinate of pane
     * @return Pane at corresponding coordinates or null if not found
     */
    public Pane getGridPaneAt(ObservableList<Node> boardChildren, int x, int y) {
        for (Node node : boardChildren) {
            if (node.getClass() == Pane.class // Eliminate non-pane children
                    && GridPane.getRowIndex(node) == y
                    && GridPane.getColumnIndex(node) == x) {
                return (Pane) node;
            }
        }

        return null;
    }

    public void update() {
        ObservableList<Move> move = getGameController().getCurrentGame().getMoves();
        if (!move.isEmpty()) {
            Move newMove = move.get(move.size() - 1); // Get last move
            ObservableList<Node> children = gameBoard.getChildren(); // Get all panes in board

            if (newMove.getClass() == Tower.class) {
                Tower tower = ((Tower) newMove);
                Pane pane = getGridPaneAt(children, tower.getX(), tower.getY());
                addTower(pane);
            } else if (newMove.getClass() == Tile.class) {
                Tile tile = ((Tile) newMove);
                Pane pane = getGridPaneAt(children, tile.getX(), tile.getY());
                Case boardCase = getGameController().getCurrentGame().getBoard().getCase(tile.getX(), tile.getY());
                addTile(pane, boardCase);
            }
        }
    }
}
