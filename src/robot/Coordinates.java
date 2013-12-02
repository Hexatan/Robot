package robot;

public class Coordinates {

    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(Object o)
    {
        Coordinates c = (Coordinates) o;
        return x == c.getX && y == c.getY;
    }
}
