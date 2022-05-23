package abTests.tests;

import abTests.model.ContactData;
import abTests.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ModificateContactTest extends BaseTest {

    @BeforeMethod
    public void ensurePrecondition() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Вован", "Вованов", null, "vovan@mail.ru", "Снурфики", "+79151591519"));
        }
    }

    @Test (enabled = false)
    public void testContactModification() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Вован", "Вованов", null, "vovan@mail.ru", "Снурфики", "+79151591519"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size() - 1;
        int id = before.get(index).getId();
        ContactData contact = new ContactData(id, "Караванов", "Караван", "Karavan", "karavan@mail.ru", null, "+79151591519");
        app.getContactHelper().modifyGroup(index, id, contact);
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
