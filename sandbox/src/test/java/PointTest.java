import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    @Test
    public void distantCheck(){
        Point p1 = new Point(0,0);
        Point p2 = new Point(3,4);
        Assert.assertEquals(p1.distance(p2), 5.0, "Расчитаное расстояние между точками не совпадает с реальным");
    }

    @Test
    public void zeroDistantCheck(){
        Point p1 = new Point(10,10);
        Point p2 = new Point(10,10);
        Assert.assertEquals(p1.distance(p2), 0, "Расчитаное расстояние между точками не равно 0");
    }
}
