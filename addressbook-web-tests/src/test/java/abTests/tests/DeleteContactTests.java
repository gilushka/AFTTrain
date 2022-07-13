package abTests.tests;

import abTests.model.ContactData;
import abTests.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactTests extends BaseTest {

    @BeforeMethod
    public void ensurePrecondition() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("Гохан")
                    .withLastName("Гоханов")
                    .withEmail1("gohan@mail.ru")
                    .withGroup("Снурфики")
                    .withMobilePhone("+79151591519"));
        }
    }

    @Test
    public void testContactDelete() throws Exception {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().mainForm();
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before.without(deletedContact)));
    }

}
