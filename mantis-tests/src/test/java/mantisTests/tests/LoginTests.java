package mantisTests.tests;

import mantisTests.appManager.HttpSession;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTests extends BaseTest {

    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();
        Assert.assertTrue(session.login("administrator", "root"));
        Assert.assertTrue(session.isLoggedInAs("administrator"));
    }
}
