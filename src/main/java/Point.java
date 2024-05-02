import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Aayush Joshi
 */
class Point extends ArrayList<Point> implements Serializable {
    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return (int)this.x;
    }
    public int getY() {
        return (int)this.y;
    }

    @Override
    public int hashCode() {
        return (int)((7 * 31 + this.x) * 31 + this.y);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if ( !( o instanceof Point p ) ) return false;
        return p.getX() == x && p.getY() == y;
    }
}
