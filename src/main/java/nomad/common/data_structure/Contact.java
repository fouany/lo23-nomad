package nomad.common.data_structure;

import java.io.Serializable;

/**
 * Represents a contact of the user
 */
public class Contact implements Serializable {

    /**
     * User description of the contact as a UserLight object
     */
    private UserLight user;

    /**
     *  Contact category as a Category object
     */
    private Category category;

    /**
     * Contact constructor
     * @param user - User description of the contact as a UserLight object
     * @param category - Contact category as a Category object
     */
    public Contact(UserLight user, Category category) {
        this.user = user;
        this.category = category;
    }

    /**
     * Returns the contact description
     * @return contact description as a UserLight object
     */
    public UserLight getUser() {
        return user;
    }

    /**
     * Sets the contact description
     * @param user - contact description as a UserLight object
     */
    public void setUser(UserLight user) {
        this.user = user;
    }

    /**
     * Returns the contact category
     * @return contact category as a Category object
     */
    public Category getCategory() {
        return category;
    }


    /**
     * Sets the contact category
     * @param category - category description as a Category object
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Returns a string representation of the object
     * @return String
     */
    @Override
    public String toString() {
        return "Contact{" +
                "user=" + user +
                ", category=" + category +
                '}';
    }
}
