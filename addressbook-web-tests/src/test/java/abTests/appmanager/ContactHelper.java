package abTests.appmanager;

import abTests.model.ContactData;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        typeValue(By.name("email"), contactCreationForm.getEmail());

        if (creation) {
            if (!wd.findElement(By.name("new_group")).getAttribute("value").equals("[none]")) {
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactCreationForm.getGroup());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

        typeValue(By.name("mobile"), contactCreationForm.getPhoneNumber());
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
        click(By.xpath("//a[@href='edit.php?id=" + id + "']"));
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
        returnToMainPage();
    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        initContactModification(contact.getId());
        fillContactCreationForm(contact, false);
        submitContactModification();
        returnToMainPage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        acceptAlert();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));
        for (WebElement element: elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastName = element.findElement(By.xpath("./td[2]")).getText();
            String firstName = element.findElement(By.xpath("./td[3]")).getText();
            String address = element.findElement(By.xpath("./td[4]")).getText();
            String allEmail = element.findElement(By.xpath("./td[5]")).getText();
            String allPhones = element.findElement(By.xpath("./td[6]")).getText();
            ContactData contact = new ContactData()
                    .withId(id)
                    .withLastName(lastName)
                    .withFirstName(firstName)
                    .withAddress(address)
                    .withEmail(allEmail)
                    .withPhoneNumber(allPhones);
            contacts.add(contact);
        }
        return contacts;
    }
}
