package abTests.tests;

import abTests.model.ContactData;
import abTests.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.List;

public class CreateTests extends BaseTest {

  @Test
  public void testContactCreate() throws Exception {
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactCreation();
    ContactData contact = new ContactData("Вован", "Вованов", null, "vovan@mail.ru", "Снурфики", "+79151591519");
    app.getContactHelper().fillContactCreationForm(contact, true);
    app.getContactHelper().submitContactCreationForm();
    app.getNavigationHelper().returnToMainForm();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
    app.getSessionHelper().logout();
  }

  @Test
  public void testGroupCreate() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    GroupData group = new GroupData("Снурфики", "Snurfics", "Сотрудники компании Снурфики");
    app.getGroupHelper().createGroup(group);
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getGroupId(), g2.getGroupId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
    app.getNavigationHelper().returnToMainForm();
    app.getSessionHelper().logout();
  }
}
