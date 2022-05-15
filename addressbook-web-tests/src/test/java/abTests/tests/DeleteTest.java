package abTests.tests;

import abTests.model.ContactData;
import abTests.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class DeleteTest extends BaseTest {


    @Test
    public void testGroupDelete() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("Снурфики", null, null));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().deleteGroup();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size()-1);
        Assert.assertEquals(after, before);

        app.getNavigationHelper().returnToMainForm();
        app.getSessionHelper().logout();
    }

    @Test
    public void testContactDelete() throws Exception {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Вован", "Вованов", null, "vovan@mail.ru", "Снурфики", "+79151591519"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().acceptAlert();
        app.getNavigationHelper().returnToMainForm();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size()-1);
        Assert.assertEquals(after, before);
        
        app.getSessionHelper().logout();
    }

}
