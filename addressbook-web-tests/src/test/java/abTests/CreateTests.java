package abTests;

import org.testng.annotations.*;

public class CreateTests extends BaseTest {

  @Test
  public void testContactCreate() throws Exception {
    initContactCreation();
    fillContactCreationForm(new ContactCreationForm("Вован", "Вованович", "Вованов", "Vovan", "15", "July", "1987", "Снурфики лимитед", "+79151591519"));
    submitContactCreationForm();
    returnToMainForm();
    logout();
  }

  @Test
  public void testGroupCreate() throws Exception {
    gotoGroupPage();
    initGroupCreation();
    fillGroupCreationForm(new GroupCreationForm("Снурфики", "Snurfics", "Сотрудники компании Снурфики"));
    submitGroupCreationForm();
    returnToMainForm();
    logout();
  }

}
