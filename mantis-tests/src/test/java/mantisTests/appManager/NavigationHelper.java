package mantisTests.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        typeValue(By.name("username"), username);
        click(By.cssSelector("input[value='Вход']"));
        typeValue(By.name("password"), password);
        click(By.cssSelector("input[value='Вход']"));
    }

    public void manageUsers() {
        click(By.xpath("//span[contains(text(),'Управление')]"));
        click(By.xpath("//a[contains(text(),'Управление пользователями')]"));
    }

    public void user(String username) {
        click(By.xpath(String.format("//a[text()='%s']", username)));
    }
}
