package abTests.tests;

import abTests.model.ContactData;
import abTests.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ModificationTest extends BaseTest {

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("Снурфики", null, null));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupCreationForm(new GroupData("Снурфики", "New Snurfics", "Сотрудники компании Новые Снурфики"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());
        app.getNavigationHelper().returnToMainForm();
        app.getSessionHelper().logout();
    }

    @Test
    public void testContactModification() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Вован", "Вованов", null, "vovan@mail.ru", "Снурфики", "+79151591519"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactCreationForm(new ContactData("Котлован", "Котлованов", "Kotlovan", "kotlovan@mail.ru", null, "+79151591519"), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returnToMainForm();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
        app.getSessionHelper().logout();
    }
}
