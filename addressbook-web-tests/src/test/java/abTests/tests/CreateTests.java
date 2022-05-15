package abTests.tests;

import abTests.model.ContactData;
import abTests.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class CreateTests extends BaseTest {

  @Test
  public void testContactCreate() throws Exception {
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactCreationForm(new ContactData("Вован", "Вованов", null, "vovan@mail.ru", "Снурфики", "+79151591519"), true);
    app.getContactHelper().submitContactCreationForm();
    app.getNavigationHelper().returnToMainForm();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
    app.getSessionHelper().logout();
  }

  @Test
  public void testGroupCreate() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().createGroup(new GroupData("Снурфики", "Snurfics", "Сотрудники компании Снурфики"));
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);
    app.getNavigationHelper().returnToMainForm();
    app.getSessionHelper().logout();
  }
}
