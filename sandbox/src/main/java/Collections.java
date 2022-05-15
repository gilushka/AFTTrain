import java.util.ArrayList;
import java.util.List;

public class Collections {

    public static void main(String[] args) {
        String[] langs = {"Java", "C#", "Python", "PHP"};

        List<String> languages = new ArrayList<String>();
        languages.add("Java");
        languages.add("C#");
        languages.add("Python");

        for (String i: langs) {
            System.out.println("Я хочу выучить " + i);
        }
    }
}
