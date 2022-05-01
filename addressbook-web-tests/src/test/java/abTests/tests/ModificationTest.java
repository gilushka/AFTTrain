package abTests.tests;

import abTests.model.ContactCreationForm;
import abTests.model.GroupCreationForm;
import org.testng.annotations.Test;

public class ModificationTest extends BaseTest {

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupCreationForm(new GroupCreationForm("Снурфики", "New Snurfics", "Сотрудники компании Новые Снурфики"));
        app.getGroupHelper().submitGroupModification();
        app.getNavigationHelper().returnToMainForm();
        app.getSessionHelper().logout();
    }

    @Test
    public void testContactModification() {
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactCreationForm(new ContactCreationForm("Котлован", "Котлованович", "Котлованов", "Kotlovan", "15", "July", "1987", "Снурфики лимитед", "+79151591519"));
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returnToMainForm();
        app.getSessionHelper().logout();
    }
}
