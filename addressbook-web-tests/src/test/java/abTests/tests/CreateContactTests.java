package abTests.tests;

import abTests.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class CreateContactTests extends BaseTest {

  @Test
  public void testContactCreate() throws Exception {
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstName("Вован")
            .withLastName("Вованов")
            .withEmail("vovan@mail.ru")
            .withGroup("Снурфики")
            .withPhoneNumber("+79151591519");
    app.contact().create(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}
