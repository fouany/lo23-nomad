package nomad.data.client;

import nomad.common.data_structure.Game;
import nomad.common.data_structure.Player;
import nomad.common.data_structure.User;
import nomad.common.data_structure.UserLight;

import java.util.Observable;
import nomad.common.data_structure.*;
import java.util.UUID;

/**
 * Controller for the current user.
 */
public class UserController extends Observable {

    /**
     * The current user
     */
    private User user;

    /**
     * Constructor
     * @param user - User : current user
     */
    public UserController(User user) {
        this.user = user;
    }

    /**
     * gets the currents user
     * @return User
     */
    public User getUser() {
        return user;
    }

    /**
     * sets the current user
     * @param user - User : current user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * sets the current user in Player format
     * @return Player
     */
    public Player getPlayer(){
        return new Player(this.user.getUserId(), this.user.getLogin(), this.user.getProfilePicture());
    }

    /**
     * sets the current user in UserLight format
     * @return UserLight
     */
    public UserLight getUserLight(){
        return new UserLight(this.user.getUserId(), this.user.getLogin());
    }

    /**
     * adds a game to the user's stored games
     * @param game - Game : game to add
     */
    public void addSavedGame(Game game){
        this.user.addSavedGame(game);
    }

    /**
     * removes a game to the user's stored games
     * @param game - Game : game to remove
     */
    public void removeSavedGame(Game game) {this.user.removeSavedGame(game);}


    /**
     * gets a games stored
     * @param gameID - UUID : id of the game
     * @return Game
     */
    public Game getGameStored(UUID gameID){
        for (Game game:
             this.user.getSavedGames()) {

            if (game.getGameId().equals(gameID)){
                return game;
            }
        }
        return null;
    }

    /**
     * creates a category
     * @param category - Category : new category
     * @throws CategoryException : category already exists
     */
    public void createCategory(Category category) throws CategoryException {
        this.user.addCategory(category);
    }

    /**
     * sets permissions of a contact
     * @param contact - Contact : contact with new permissions
     * @throws CategoryException : Contact doesn't exist
     */
    public void setPermissions(Contact contact) throws CategoryException {
        for (Contact c: this.user.getContacts()
        ) {
            UUID lastContact = c.getUser().getId();
            UUID newContact = contact.getUser().getId();

            if (lastContact.equals(newContact)){
                c.setCategory(contact.getCategory());
                return;
            }
        }
        throw new CategoryException ("Contact doesn't exist");
    }

    /**
     * adds a user to a category
     * @param user - UserLight : user to add
     * @param category - Category : category
     * @throws CategoryException : Category doesn't exist
     */
    public void addUser(UserLight user, Category category) throws CategoryException {this.user.addUser(user, category);}

    /**
     * sets the permissions of a category
     * @param lastCategory - Category : category with old permissions
     * @param updatedCategory - Category : category with new permissions
     * @throws CategoryException : Category doesn't exist
     */
    public void setPermissions(Category lastCategory, Category updatedCategory) throws CategoryException {
        this.user.updateCategory(lastCategory, updatedCategory);
        for (Contact c : this.user.getContacts()){
            if (c.getCategory().getName().equals(lastCategory.getName())){
                c.setCategory(updatedCategory);
                return;
            }
        }
        throw new CategoryException("Category doesn't exist");
    }

}
