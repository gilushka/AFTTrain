package abTests.tests;

import abTests.model.ContactCreationForm;
import abTests.model.GroupCreationForm;
import org.testng.annotations.*;

public class CreateTests extends BaseTest {

  @Test
  public void testContactCreate() throws Exception {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactCreationForm(new ContactCreationForm("Вован", "Вованович", "Вованов", "Vovan", "1987", "Снурфики", "Снурфики лимитед", "+79151591519"), true);
    app.getContactHelper().submitContactCreationForm();
    app.getNavigationHelper().returnToMainForm();
    app.getSessionHelper().logout();
  }

  @Test
  public void testGroupCreate() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().createGroup(new GroupCreationForm("Снурфики", "Snurfics", "Сотрудники компании Снурфики"));
    app.getNavigationHelper().returnToMainForm();
    app.getSessionHelper().logout();
  }
}
