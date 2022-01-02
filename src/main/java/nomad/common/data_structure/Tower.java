package nomad.common.data_structure;

/**
 * Represents a tower on a case of the board
 */
public class Tower extends Move {

    /**
     * Horizontal position of the tower
     */
    private int x;

    /**
     * Vertical position of the tower
     */
    private int y;

    /**
     * @param x - Horizontal position of the case
     * @param y - Vertical position of the case
     */
    public Tower(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the horizontal position of the tower
     * @return int
     */
    public int getX() {
        return x;
    }

    /**
     * Sets vertical position of the tower
     * @param x - int
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the vertical position of the tower
     * @return int
     */
    public int getY() {
        return y;
    }

    /**
     * Sets vertical position of the tower
     * @param y - int
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns a string representation of the object
     * @return String
     */
    @Override
    public String idMove(){return "Tower";}

    @Override
    public String toString() {
        return "Tower{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
