package nomad.data.server;

import nomad.common.data_structure.User;

import java.util.*;

/**
 * Controller for an user.
 */
public class UserController {

    /**
     * The map of Users currently connected
     */
    private Map<UUID, User> users;

    public UserController(){
        users = new HashMap<>();
    }

    /**
     * Sets the user.
     * @param user - User : the new user
     */
    public void setUser(User user){
        users.put(user.getUserId(), user);
    }

    /**
     * Returns the controlled user.
     * @return user - User : the controlled user
     */
    public User getUser(UUID userId){
        return users.get(userId);
    }

    /**
     * Gets all users
     * @returns List<User> : all the Users
     */
    public List<User> getAllUsers(){
        return new ArrayList<>(users.values());
    }

    /**
     * Removes a user
     * @param userId - UUID : the ID of the User who has to be removed
     * @return User : the deleted User
     */
    public User removeUser(UUID userId){
        return users.remove(userId);
    }
}
