package model;

import java.io.Serializable;

public class Contact implements Serializable {

    private UserLight user;
    private Category category;

    public Contact(UserLight user, Category category) {
        this.user = user;
        this.category = category;
    }

    public UserLight getUser() {
        return user;
    }

    public void setUser(UserLight user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "user=" + user +
                ", category=" + category +
                '}';
    }
}
