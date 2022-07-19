package abTests.tests;

import abTests.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactDataTests extends BaseTest {

    @BeforeMethod
    public void ensurePrecondition() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("Вован")
                    .withLastName("Вованов")
                    .withAddress("Милый дом")
                    .withHomePhone("444")
                    .withMobilePhone("555")
                    .withWorkPhone("666")
                    .withEmail1("first@email.ru")
                    .withEmail2("second@email.ru")
                    .withEmail3("third-email ru")
                    .withSecondPhone("777"));
        }
    }

    @Test
    public void testContactPhones() {
        app.goTo().mainForm();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }
}
