package nomad.common.data_structure;

public class Tower extends Move {

    private int x;
    private int y;

    public Tower(int x, int y) {
        this.x = x;
        this.y = y;
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

    @Override
    public String toString() {
        return "Tower{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
