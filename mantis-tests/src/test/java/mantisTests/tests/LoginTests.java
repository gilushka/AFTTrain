package mantisTests.tests;

import mantisTests.appManager.HttpSession;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTests extends BaseTest {

    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();
        Assert.assertTrue(session.login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword")));
        Assert.assertTrue(session.isLoggedInAs(app.getProperty("web.adminLogin")));
    }
}
