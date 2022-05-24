package abTests.tests;

import abTests.model.ContactData;
import abTests.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class DeleteContactTests extends BaseTest {

    @BeforeMethod
    public void ensurePrecondition() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData()
                    .withFirstName("Гохан")
                    .withLastName("Гоханов")
                    .withEmail("gohan@mail.ru")
                    .withGroup("Снурфики")
                    .withPhoneNumber("+79151591519"));
        }
    }

    @Test
    public void testContactDelete() throws Exception {
        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size() - 1;
        app.getContactHelper().deleteContact(index);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(after, before);
    }

}
