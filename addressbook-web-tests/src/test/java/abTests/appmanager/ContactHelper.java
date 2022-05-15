package abTests.appmanager;

import abTests.model.ContactData;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
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
        typeValue(By.name("adress"), contactCreationForm.getAdress());
        typeValue(By.name("email"), contactCreationForm.getEMail());

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

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void initContactModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void returnToMainPage() {
        click(By.linkText("home page"));
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactCreationForm(contact, true);
        submitContactCreationForm();
        returnToMainPage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> groups = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.xpath("//tbody/tr/td[2]"));
        for (WebElement element: elements) {
            String name = element.getText();
            ContactData group = new ContactData(name, null, null, null, null, null);
            groups.add(group);
        }
        return groups;
    }
}
