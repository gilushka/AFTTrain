package abTests.tests;

import abTests.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class DeleteContactTests extends BaseTest {

    @BeforeMethod
    public void ensurePrecondition() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("Гохан")
                    .withLastName("Гоханов")
                    .withEmail("gohan@mail.ru")
                    .withGroup("Снурфики")
                    .withPhoneNumber("+79151591519"));
        }
    }

    @Test
    public void testContactDelete() throws Exception {
        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().mainForm();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedContact);
        Assert.assertEquals(after, before);
    }

}
