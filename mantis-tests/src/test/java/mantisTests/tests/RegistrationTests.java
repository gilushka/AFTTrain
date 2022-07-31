package mantisTests.tests;

import org.testng.annotations.Test;

public class RegistrationTests extends BaseTest {

    @Test
    public void testRegistration() {
        app.registration().start("user1", "user1@localhost.localdomain");
    }
}
