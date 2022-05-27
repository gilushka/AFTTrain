package abTests.tests;

import abTests.model.ContactData;
import abTests.model.Contacts;
import abTests.model.Groups;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class CreateContactTests extends BaseTest {

  @Test
  public void testContactCreate() throws Exception {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstName("Вован")
            .withLastName("Вованов")
            .withEmail("vovan@mail.ru")
            .withGroup("Снурфики")
            .withPhoneNumber("+79151591519");
    app.contact().create(contact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() + 1);

    assertThat(after, equalTo(before
            .withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
