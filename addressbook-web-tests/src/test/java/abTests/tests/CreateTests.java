package abTests.tests;

import abTests.model.ContactData;
import abTests.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashSet;
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

    contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
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

    group.setGroupId(after.stream().max((o1, o2) -> Integer.compare(o1.getGroupId(), o2.getGroupId())).get().getGroupId());
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    app.getNavigationHelper().returnToMainForm();
    app.getSessionHelper().logout();
  }
}
