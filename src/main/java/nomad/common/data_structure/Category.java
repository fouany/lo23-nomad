package nomad.common.data_structure;

import java.io.Serializable;

/**
 * Represents a category of contacts
 */
public class Category implements Serializable {

    /**
     * Name of the category
     */
    private String name;
    /**
     * Right for the contacts in the category to see the user's profile
     */
    private boolean checkInfos;
    /**
     * Right for the contacts in the category to watch a previous game of the owner
     */
    private boolean rightToWatch;
    /**
     * Right for the contacts in the category to play the game with the owner
     */
    private boolean rightToParticipate;
    /**
     * Right for the contacts in the category to join the owner's game as spectators
     */
    private boolean accessAllowed;

    /**
     * Category constructor
     * @param name - name of the category
     * @param checkInfos - Right for the contacts in the category to watch a previous game of the owner
     * @param rightToWatch - Right for the contacts in the category to play the game with the owner
     * @param rightToParticipate - Right for the contacts in the category to play the game with the owner
     * @param accessAllowed - Right for the contacts in the category to join the owner's game as spectators
     */
    public Category(String name, boolean checkInfos, boolean rightToWatch, boolean rightToParticipate, boolean accessAllowed) {
        this.name = name;
        this.checkInfos = checkInfos;
        this.rightToWatch = rightToWatch;
        this.rightToParticipate = rightToParticipate;
        this.accessAllowed = accessAllowed;
    }

    /**
     * Returns the name of the category
     * @return the name of the category as a string
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the category
     * @param name  - name of the category
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the right for the contacts in the category to see the user's profile
     * @return Right as a boolean
     */
    public boolean isCheckInfos() {
        return checkInfos;
    }

    /**
     * Sets the right for the contacts in the category to see the user's profile
     * @param checkInfos - Right as a boolean
     */
    public void setCheckInfos(boolean checkInfos) {
        this.checkInfos = checkInfos;
    }

    /**
     * Returns the right for the contacts in the category to watch a previous game of the owner
     * @return Right as a boolean
     */
    public boolean isRightToWatch() {
        return rightToWatch;
    }

    /**
     * Sets the right for the contacts in the category to watch a previous game of the owner
     * @param rightToWatch - Right as a boolean
     */
    public void setRightToWatch(boolean rightToWatch) {
        this.rightToWatch = rightToWatch;
    }

    /**
     * Returns the right for the contacts in the category to play the game with the owner
     * @return Right as a boolean
     */
    public boolean isRightToParticipate() {
        return rightToParticipate;
    }

    /**
     * Sets the right for the contacts in the category to play the game with the owner
     * @param rightToParticipate - Right as a boolean
     */
    public void setRightToParticipate(boolean rightToParticipate) {
        this.rightToParticipate = rightToParticipate;
    }

    /**
     * Returns the right for the contacts in the category to join the owner's game as spectators
     * @return Right as a boolean
     */
    public boolean isAccessAllowed() {
        return accessAllowed;
    }

    /**
     * Returns the right for the contacts in the category to join the owner's game as spectators
     * @param accessAllowed - Right as a boolean
     */
    public void setAccessAllowed(boolean accessAllowed) {
        this.accessAllowed = accessAllowed;
    }

    /**
     * Returns a string representation of the object
     * @return String
     */
    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", checkInfos=" + checkInfos +
                ", rightToWatch=" + rightToWatch +
                ", rightToParticipate=" + rightToParticipate +
                ", accessAllowed=" + accessAllowed +
                '}';
    }
}
