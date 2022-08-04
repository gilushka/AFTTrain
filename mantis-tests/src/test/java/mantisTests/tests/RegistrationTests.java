package mantisTests.tests;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import mantisTests.model.MailMessage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.*;

public class RegistrationTests extends BaseTest {

    @BeforeTest
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testRegistration() throws MessagingException, IOException {
        long now = System.currentTimeMillis();
        String user = String.format("user%s", now);
        String password = "password";
        String email = String.format("user%s@localhost.localdomain", now);
        app.registration().start(user, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(user, password));
    }

    @AfterTest(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
