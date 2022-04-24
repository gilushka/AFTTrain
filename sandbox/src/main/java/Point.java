import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class Point {

    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point p){
        int x = abs(this.x - p.x);
        int y = abs(this.y - p.y);
        return sqrt(x*x + y*y);
    }
}
