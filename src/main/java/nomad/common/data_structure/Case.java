package nomad.common.data_structure;

import java.io.Serializable;

/**
 * Represents a case of the board
 */
public class Case implements Serializable {

    /**
     * Horizontal position of the case
     */
    private int x;

    /**
     * Vertical position of the case
     */
    private int y;

    /**
     * Height of the case
     */
    private int height;

    /**
     * if the case is a tower
     */
    private boolean isTower;

    /**
     * Represents the owner of the case
     * (0 = black, 1 = red)
     */
    private boolean color;

    /**
     * Case constructor
     * @param x - Horizontal position of the case
     * @param y - Vertical position of the case
     * @param height - Height of the case
     * @param isTower - True if the case is a tower
     * @param color - Represents the owner of the case
     */
    public Case(int x, int y, int height, boolean isTower, boolean color) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.isTower = isTower;
        this.color = color;
    }

    /**
     * Returns the horizontal position of the case
     * @return int
     */
    public int getX() {
        return x;
    }

    /**
     * Sets vertical position of the case
     * @param x - int
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the vertical position of the case
     * @return int
     */
    public int getY() {
        return y;
    }

    /**
     * Sets vertical position of the case
     * @param y - int
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns the height of the case
     * @return int
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the case
     * @param height - int
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Increments the height parameter of the case by 1
     */
    public void incrementHeight() { height += 1;}

    /**
     * Returns True if the case is a Tower
     * @return boolean
     */
    public boolean isTower() {
        return isTower;
    }

    /**
     * Sets the case if a tower is placed
     * @param tower - boolean
     */
    public void setTower(boolean tower) {
        isTower = tower;
    }

    /**
     * Returns the owner of the case
     * @return boolean
     */
    public boolean isColor() {
        return color;
    }

    /**
     * Sets the color of the case
     * @param color - Represents the owner of the case (0 = black, 1 = red)
     */
    public void setColor(boolean color) {
        this.color = color;
    }

    /**
     * Returns a string representation of the object
     * @return String
     */
    @Override
    public String toString() {
        return "Case{" +
                "x=" + x +
                ", y=" + y +
                ", height=" + height +
                ", isTower=" + isTower +
                ", color=" + color +
                '}';
    }
}
