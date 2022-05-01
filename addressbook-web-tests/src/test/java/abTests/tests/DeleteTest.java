package abTests.tests;

import org.testng.annotations.Test;

public class DeleteTest extends BaseTest {


    @Test
    public void testGroupDelete() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteGroup();
        app.getGroupHelper().returnToGroupPage();
        app.getNavigationHelper().returnToMainForm();
        app.getSessionHelper().logout();
    }

    @Test
    public void testContactDelete() throws Exception {
        app.getContactHelper().selectContact("9");
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().acceptAlert();
        app.getNavigationHelper().returnToMainForm();
        app.getSessionHelper().logout();
    }

}
