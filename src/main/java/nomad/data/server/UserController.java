package nomad.data.server;

import nomad.common.data_structure.User;

import java.util.*;

/**
 * Controller for an user.
 */
public class UserController {
    private Map<UUID, User> users;

    public UserController(){
        users = new HashMap<>();
    }

    /**
     * Sets the user.
     * @param user
     */
    public void setUser(User user){
        users.put(user.getUserId(), user);
    }

    /**
     * Returns the controlled user.
     * @return user
     */
    public User getUser(UUID userId){
        return users.get(userId);
    }

    /**
     * Get all users
     */
    public List<User> getAllUsers(){
        return new ArrayList<>(users.values());
    }

    /**
     * Removes an user
     */
    public User removeUser(UUID userId){
        return users.remove(userId);
    }
}
