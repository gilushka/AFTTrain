package abTests.tests;

import abTests.model.ContactCreationForm;
import abTests.model.GroupCreationForm;
import org.testng.annotations.Test;

public class DeleteTest extends BaseTest {


    @Test
    public void testGroupDelete() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupCreationForm("Снурфики", null, null));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteGroup();
        app.getGroupHelper().returnToGroupPage();
        app.getNavigationHelper().returnToMainForm();
        app.getSessionHelper().logout();
    }

    @Test
    public void testContactDelete() throws Exception {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactCreationForm("Вован", null, "Вованов", null, null, "Снурфики", null, "+79151591519"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().acceptAlert();
        app.getNavigationHelper().returnToMainForm();
        app.getSessionHelper().logout();
    }

}
