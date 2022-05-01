package abTests.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {
    private WebDriver wd;

    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    public void authorize(String login, String password) {
        typeValue(By.name("user"), login);
        typeValue(By.name("pass"), password);
        click(By.xpath("//input[@value='Login']"));
    }

    public void logout() {
        click(By.linkText("Logout"));
    }
}
