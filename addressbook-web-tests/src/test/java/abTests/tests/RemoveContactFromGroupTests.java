package abTests.tests;

import abTests.model.ContactData;
import abTests.model.Contacts;
import abTests.model.GroupData;
import abTests.model.Groups;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroupTests extends BaseTest{

    @Test
    public void testRemoveContactFromGroup(){
        Groups groups = app.db().groups();
        //Проверяем что хотя бы одна группа существует, иначе создаем новую
        checkGroupExisting(groups);
        groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        //Проверяем, что хотя бы один контакт существует, иначе создаем новый
        checkContactExisting(contacts);
        //Получаем контакт входящий хотя бы в одну группу
        ContactData contact = getContactWithGroup(contacts, groups);

        app.contact().removeGroup(contact);
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(contacts.without(contact).withAdded(contact)));
    }


    private ContactData getContactWithGroup(Contacts contacts, Groups groups) {
        ContactData contact = chooseContactWithGroup(contacts);
        if (contact == null) {
            contact = contacts.iterator().next();
            app.contact().addGroup(contact, groups.iterator().next());
            app.goTo().mainForm();
            contacts = app.db().contacts();
            contact = chooseContactWithGroup(contacts);
        }
        return contact;
    }

    private ContactData chooseContactWithGroup(Contacts contacts) {
        for (ContactData contact: contacts) {
            if (contact.getGroups().size() > 0) {
                return contact;
            }
        }
        return null;
    }

    private GroupData chooseAddingGroup(ContactData contact, Groups groups) {
        for (GroupData group: groups) {
            if (!contact.getGroups().contains(group)) {
                return group;
            }
        }
        Assert.fail("Отсутствует доступная для выбора группа!");
        return null;
    }
}
