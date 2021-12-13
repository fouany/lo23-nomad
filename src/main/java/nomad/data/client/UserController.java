package nomad.data.client;

import nomad.common.data_structure.Game;
import nomad.common.data_structure.Player;
import nomad.common.data_structure.User;
import nomad.common.data_structure.UserLight;

import java.util.Observable;
import nomad.common.data_structure.*;
import java.util.UUID;

public class UserController extends Observable {

    private User user;

    public UserController(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Player getPlayer(){
        return new Player(this.user.getUserId(), this.user.getLogin(), this.user.getProfilePicture());
    }

    public UserLight getUserLight(){
        return new UserLight(this.user.getUserId(), this.user.getLogin());
    }

    public void addSavedGame(Game game){
        this.user.addSavedGame(game);
    }

    public void removeSavedGame(Game game) {this.user.removeSavedGame(game);}


    public Game getGameStored(UUID gameID){
        for (Game game:
             this.user.getSavedGames()) {

            if (game.getGameId().equals(gameID)){
                return game;
            }
        }
        return null;
    }

    public void createCategory(Category category) throws CategoryException {
        this.user.addCategory(category);
    }

    public void setPermissions(Contact contact) throws CategoryException {
        for (Contact c: this.user.getContacts()
        ) {
            UUID lastContact = contact.getUser().getId();
            UUID newContact = contact.getUser().getId();

            if (lastContact.equals(newContact)){
                c.setCategory(contact.getCategory());
                return;
            }
        }
        throw new CategoryException ("Contact doesn't exist");
    }

    public void addUser(UserLight user, Category category) throws CategoryException {this.user.addUser(user, category);}

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
