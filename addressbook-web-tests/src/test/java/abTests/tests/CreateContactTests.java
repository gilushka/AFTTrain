package abTests.tests;

import abTests.model.ContactData;
import abTests.model.Contacts;
import org.testng.annotations.Test;

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
            .withEmail1("vovan@mail.ru")
            .withGroup("Снурфики")
            .withMobilePhone("+79151591519");
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();

    assertThat(after, equalTo(before
            .withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
