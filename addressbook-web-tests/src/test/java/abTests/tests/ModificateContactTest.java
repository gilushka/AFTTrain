package abTests.tests;

import abTests.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class ModificateContactTest extends BaseTest {

    @BeforeMethod
    public void ensurePrecondition() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("Вован")
                    .withLastName("Вованов")
                    .withEmail("vovan@mail.ru")
                    .withGroup("Снурфики")
                    .withPhoneNumber("+79151591519"));
        }
    }

    @Test
    public void testContactModification() {
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstName("Караван")
                .withLastName("Караванов")
                .withAddress("Karavan")
                .withEmail("karavan@mail.ru")
                .withPhoneNumber("+79151591519");
        app.contact().modify(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
