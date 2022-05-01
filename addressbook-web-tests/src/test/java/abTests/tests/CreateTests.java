package abTests.tests;

import abTests.model.ContactCreationForm;
import abTests.model.GroupCreationForm;
import org.testng.annotations.*;

public class CreateTests extends BaseTest {

  @Test
  public void testContactCreate() throws Exception {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactCreationForm(new ContactCreationForm("Вован", "Вованович", "Вованов", "Vovan", "15", "July", "1987", "Снурфики лимитед", "+79151591519"));
    app.getContactHelper().submitContactCreationForm();
    app.getNavigationHelper().returnToMainForm();
    app.getSessionHelper().logout();
  }

  @Test
  public void testGroupCreate() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupCreationForm(new GroupCreationForm("Снурфики", "Snurfics", "Сотрудники компании Снурфики"));
    app.getGroupHelper().submitGroupCreationForm();
    app.getNavigationHelper().returnToMainForm();
    app.getSessionHelper().logout();
  }

}
