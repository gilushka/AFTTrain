package abTests.tests;

import abTests.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ModificateContactTest extends BaseTest {

    @BeforeMethod
    public void ensurePrecondition() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData()
                    .withFirstName("Вован")
                    .withLastName("Вованов")
                    .withEmail("vovan@mail.ru")
                    .withGroup("Снурфики")
                    .withPhoneNumber("+79151591519"));
        }
    }

    @Test
    public void testContactModification() {
        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size() - 1;
        int id = before.get(index).getId();
        ContactData contact = new ContactData()
                .withId(id)
                .withFirstName("Караван")
                .withLastName("Караванов")
                .withAddress("Karavan")
                .withEmail("karavan@mail.ru")
                .withPhoneNumber("+79151591519");
        app.getContactHelper().modifyContact(index, id, contact);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
