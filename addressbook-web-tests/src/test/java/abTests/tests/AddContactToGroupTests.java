package abTests.tests;

import abTests.model.ContactData;
import abTests.model.Contacts;
import abTests.model.GroupData;
import abTests.model.Groups;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends BaseTest{

    @Test
    public void testAddContactToGroup(){
        Groups groups = app.db().groups();
        //Проверяем что хотя бы одна группа существует, иначе создаем новую
        checkGroupExisting(groups);
        groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        //Проверяем, что хотя бы один контакт существует, иначе создаем новый
        checkContactExisting(contacts);
        ContactData contact = contacts.iterator().next();
        //Проверяем, что выбранный контакт не входит во все имеющиеся группы, иначе создаем новую
        checkContactInAllGroups(contact, groups);
        groups = app.db().groups();
        contact = app.db().contacts().iterator().next();
        GroupData group = chooseAddingGroup(contact, groups);
        app.contact().addGroup(contact, group);
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(contacts.without(contact).withAdded(contact.withGroups(group))));
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

    private void checkContactInAllGroups(ContactData contact, Groups groups) {
        if (contact.getGroups().size() == groups.size()) {
            addGroupInAddressbook(groups);
        }
    }
}
