package mantisTests.tests;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import mantisTests.model.MailMessage;
import mantisTests.model.User;
import mantisTests.model.Users;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends BaseTest {

    @BeforeTest
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangePassword() throws MessagingException, IOException {
        long now = System.currentTimeMillis();
        User user = app.db().users().iterator().next();
        String userName = user.getUsername();
        String password = String.format("password%s", now);
        String email = user.getEmail();
        //Шаг 1
        app.goTo().login(app.getProperty("admin.login"), app.getProperty("admin.password"));
        app.goTo().manageUsers();
        app.goTo().user(userName);
        //Шаг 2
        app.changePassword().init();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
        //Шаг 3
        assertTrue(app.newSession().login(userName, password));
    }

    @AfterTest(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
