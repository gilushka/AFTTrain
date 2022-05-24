package abTests.tests;

import abTests.model.ContactData;
import abTests.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class CreateContactTests extends BaseTest {

  @Test
  public void testContactCreate() throws Exception {
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData()
            .withFirstName("Вован")
            .withLastName("Вованов")
            .withEmail("vovan@mail.ru")
            .withGroup("Снурфики")
            .withPhoneNumber("+79151591519");
    app.getContactHelper().createContact(contact);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
