package nomad.common.data_structure;

/**
 * Represents a tile on a case of the board
 */
public class Tile extends Move {

    /**
     * Horizontal position of the tile
     */
    private int x;

    /**
     * Vertical position of the tile
     */
    private int y;

    /**
     * Represents the owner of the tile
     * (0 = black, 1 = red)
     */
    private boolean color;

    /**
     * Tile constructor
     * @param x - Horizontal position of the case
     * @param y - Vertical position of the case
     * @param color - Represents the owner of the case
     */
    public Tile(int x, int y, boolean color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    /**
     * Returns the horizontal position of the tile
     * @return int
     */
    public int getX() {
        return x;
    }

    /**
     * Sets vertical position of the tile
     * @param x - int
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the vertical position of the tile
     * @return int
     */
    public int getY() {
        return y;
    }

    /**
     * Sets vertical position of the tile
     * @param y - int
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns the owner of the tile
     * @return boolean
     */
    public boolean isColor() {
        return color;
    }

    /**
     * Sets the color of the tile
     * @param color - Represents the owner of the tile (0 = black, 1 = red)
     */
    public void setColor(boolean color) {
        this.color = color;
    }

    /**
     * Returns a string representation of the object
     * @return String
     */
    @Override
    public String idMove(){return "Tile";}

    @Override
    public String toString() {
        return "Tile{" +
                "x=" + x +
                ", y=" + y +
                ", color=" + color +
                '}';
    }
}
