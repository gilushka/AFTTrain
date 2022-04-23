import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class FirstProgram {
	public static void main(String[] args){
		System.out.println("Задачи первого урока.\n");

		Point p1 = new Point(10,23);
		Point p2 = new Point(3,78);
		System.out.println("Расстояние между заданными точками равно:" + distance(p1, p2));
	}

	public static double distance(Point p1, Point p2){
		int x = abs(p1.x - p2.x);
		int y = abs(p1.y - p2.y);
		return sqrt(x*x + y*y);
	}
}