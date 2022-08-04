package mantisTests.appManager;

import org.openqa.selenium.By;

public class ChangePasswordHelper extends HelperBase {

    public ChangePasswordHelper(ApplicationManager app) {
        super(app);
    }

    public void init() {
        click(By.cssSelector("input[value='Сбросить пароль']"));
    }
}
