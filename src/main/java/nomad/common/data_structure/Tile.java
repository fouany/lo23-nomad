package nomad.common.data_structure;

public class Tile extends Move {

    private int x;
    private int y;
    private boolean color;

    public Tile(int x, int y, boolean color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

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
