package abTests;

import org.testng.annotations.Test;

public class DeleteTest extends BaseTest {


    @Test
    public void testGroupDelete() throws Exception {
        gotoGroupPage();
        selectGroup();
        deleteGroup();
        returnToGroupPage();
        returnToMainForm();
        logout();
    }

}
