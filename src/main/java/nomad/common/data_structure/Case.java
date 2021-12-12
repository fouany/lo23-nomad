package nomad.common.data_structure;

import java.io.Serializable;

public class Case implements Serializable {

    private int x;
    private int y;
    private int height;
    private boolean isTower;
    private boolean color;

    public Case(int x, int y, int height, boolean isTower, boolean color) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.isTower = isTower;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void incrementHeight() { height += 1;}

    public boolean isTower() {
        return isTower;
    }

    public void setTower(boolean tower) {
        isTower = tower;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

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
