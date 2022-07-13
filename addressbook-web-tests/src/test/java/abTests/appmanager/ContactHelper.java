package abTests.appmanager;

import abTests.model.ContactData;
import abTests.model.Contacts;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreationForm() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactCreationForm(ContactData contactCreationForm, boolean creation) {
        typeValue(By.name("firstname"), contactCreationForm.getFirstName());
        typeValue(By.name("lastname"), contactCreationForm.getLastName());
        typeValue(By.name("address"), contactCreationForm.getAddress());
        typeValue(By.name("email"), contactCreationForm.getEmail1());
        typeValue(By.name("email2"), contactCreationForm.getEmail2());
        typeValue(By.name("email3"), contactCreationForm.getEmail3());
        attach(By.name("photo"), contactCreationForm.getPhoto());

        if (creation) {
            if (!wd.findElement(By.name("new_group")).getAttribute("value").equals("[none]")) {
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactCreationForm.getGroup());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

        typeValue(By.name("home"), contactCreationForm.getHomePhone());
        typeValue(By.name("mobile"), contactCreationForm.getMobilePhone());
        typeValue(By.name("work"), contactCreationForm.getWorkPhone());
        typeValue(By.name("phone2"), contactCreationForm.getPhone2());
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void acceptAlert() {
        Alert alert = wd.switchTo().alert();
        alert.accept();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void selectContactById(int id) {
        wd.findElement(By.id(String.valueOf(id))).click();
//        wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
    }

    public void initContactModification(int id) {
//        click(By.xpath("//a[@href='edit.php?id=" + id + "']"));
        click(By.xpath(String.format("//a[@href='edit.php?id=%s']", id)));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void returnToMainPage() {
        click(By.linkText("home page"));
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactCreationForm(contact, true);
        submitContactCreationForm();
        contactCache = null;
        returnToMainPage();
    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        initContactModification(contact.getId());
        fillContactCreationForm(contact, false);
        submitContactModification();
        contactCache = null;
        returnToMainPage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        acceptAlert();
        contactCache = null;
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }

        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));
        for (WebElement element: elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allPhones = cells.get(5).getText();
            String allEmail = cells.get(4).getText();
            ContactData contact = new ContactData()
                    .withId(id)
                    .withLastName(lastName)
                    .withFirstName(firstName)
                    .withAddress(address)
                    .withAllPhones(allPhones)
                    .withAllEmails(allEmail);
            contactCache.add(contact);
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModification(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email1 = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String phone2 = wd.findElement(By.name("phone2")).getAttribute("value");
        wd.navigate().back();
        return new ContactData()
                .withId(contact.getId())
                .withFirstName(firstName)
                .withLastName(lastName)
                .withAddress(address)
                .withHomePhone(home)
                .withMobilePhone(mobile)
                .withWorkPhone(work)
                .withEmail1(email1)
                .withEmail2(email2)
                .withEmail3(email3)
                .withSecondPhone(phone2);
    }
}
