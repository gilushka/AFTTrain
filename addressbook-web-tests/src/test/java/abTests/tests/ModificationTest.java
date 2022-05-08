package abTests.tests;

import abTests.model.ContactCreationForm;
import abTests.model.GroupCreationForm;
import org.testng.annotations.Test;

public class ModificationTest extends BaseTest {

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupCreationForm("Снурфики", null, null));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupCreationForm(new GroupCreationForm("Снурфики", "New Snurfics", "Сотрудники компании Новые Снурфики"));
        app.getGroupHelper().submitGroupModification();
        app.getNavigationHelper().returnToMainForm();
        app.getSessionHelper().logout();
    }

    @Test
    public void testContactModification() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactCreationForm("Вован", null, "Вованов", null, null, "Снурфики", null, "+79151591519"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactCreationForm(new ContactCreationForm("Котлован", "Котлованович", "Котлованов", "Kotlovan", "1987", null, "Снурфики лимитед", "+79151591519"), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returnToMainForm();
        app.getSessionHelper().logout();
    }
}
