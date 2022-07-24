package abTests.tests;

import abTests.appmanager.ApplicationManager;
import abTests.model.ContactData;
import abTests.model.Contacts;
import abTests.model.GroupData;
import abTests.model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class BaseTest {

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() throws Exception {
        app.stop();
    }

    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            assertThat(uiGroups, equalTo(dbGroups.stream()
                    .map((g) -> new GroupData()
                            .withGroupId(g.getGroupId())
                            .withGroupName(g.getGroupName()))
                    .collect(Collectors.toSet())));
        }
    }

    public void verifyContactListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.contact().all();
            assertThat(uiContacts, equalTo(dbContacts.stream()
                    .map((g) -> new ContactData()
                            .withId(g.getId())
                            .withFirstName(g.getFirstName())
                            .withLastName(g.getLastName())
                            .withAddress(g.getAddress())
                            .withAllPhones(mergePhones(new ContactData()
                                    .withHomePhone(g.getHomePhone())
                                    .withMobilePhone(g.getMobilePhone())
                                    .withWorkPhone(g.getWorkPhone())
                                    .withSecondPhone(g.getPhone2())))
                            .withAllEmails(mergeEmails((new ContactData()
                                    .withEmail1(g.getEmail1())
                                    .withEmail2(g.getEmail2())
                                    .withEmail3(g.getEmail3())))))
                    .collect(Collectors.toSet())));
        }
    }

    public String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail1(), contact.getEmail2(), contact.getEmail3())
                .stream().filter(s -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    public String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getPhone2())
                .stream().filter(s -> ! s.equals(""))
                .map(ContactDataTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public void checkGroupExisting(Groups groups) {
        if (groups.size() == 0) {
            addGroupInAddressbook(groups);
        }
    }

    public void checkContactExisting(Contacts contacts) {
        if (contacts.size() == 0) {
            File photo = new File("src/test/resources/photo1.png");
            ContactData contact = new ContactData()
                    .withFirstName("Перилидон")
                    .withLastName("Перилидонский")
                    .withAddress("")
                    .withEmail1("perilidon@mail.ru")
                    .withEmail2("")
                    .withEmail3("")
                    .withPhoto(photo)
                    .withMobilePhone("+79153573757")
                    .withHomePhone("")
                    .withWorkPhone("")
                    .withSecondPhone("");
            app.contact().create(contact);
            assertThat(app.contact().count(), equalTo(1));
            contacts.add(contact);
        }
    }

    public void addGroupInAddressbook(Groups groups) {
        int groupCount = groups.size();
        app.goTo().groupPage();
        GroupData group = createNewGroup();
        app.group().create(group);
        assertThat(app.group().count(), equalTo(groupCount + 1));
        app.goTo().mainForm();
    }

    public GroupData createNewGroup() {
        return new GroupData()
                .withGroupName("Предусловное имя")
                .withGroupHeader("Предусловный хэдер")
                .withGroupFooter("Предусловный футер");
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

}
