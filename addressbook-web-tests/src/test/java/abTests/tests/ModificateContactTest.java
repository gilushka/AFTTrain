package abTests.tests;

import abTests.model.ContactData;
import abTests.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ModificateContactTest extends BaseTest {

    @BeforeMethod
    public void ensurePrecondition() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("Вован")
                    .withLastName("Вованов")
                    .withEmail1("vovan@mail.ru")
                    .withGroup("Снурфики")
                    .withMobilePhone("+79151591519"));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstName("Караван")
                .withLastName("Караванов")
                .withAddress("Karavan")
                .withEmail1("karavan@mail.ru")
                .withEmail2("")
                .withEmail3("")
                .withHomePhone("")
                .withMobilePhone("+79151591519")
                .withWorkPhone("")
                .withSecondPhone("");
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
