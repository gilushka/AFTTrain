package mantisTests.appManager;

import org.openqa.selenium.WebDriver;

public class RegistrationHelper {
    private final ApplicationManager app;
    private WebDriver wd;

    public RegistrationHelper(ApplicationManager app) {
        this.app = app;
        wd = app.getDriver();
    }

    public void start(String user, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    }
}
