package abTests.tests;

import abTests.model.ContactData;
import abTests.model.Contacts;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstName("Караван")
                .withLastName("Караванов")
                .withAddress("Karavan")
                .withEmail("karavan@mail.ru")
                .withPhoneNumber("+79151591519");
        app.contact().modify(contact);
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
