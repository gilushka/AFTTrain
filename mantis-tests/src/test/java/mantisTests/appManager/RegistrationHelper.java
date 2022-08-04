package mantisTests.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationHelper extends HelperBase {

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        typeValue(By.name("username"), username);
        typeValue(By.name("email"), email);
        click(By.cssSelector("input[value='Зарегистрироваться']"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        typeValue(By.name("password"), password);
        typeValue(By.name("password_confirm"), password);
        click(By.cssSelector("button[type='submit']"));
    }
}
