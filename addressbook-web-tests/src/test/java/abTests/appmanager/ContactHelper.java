package abTests.appmanager;

import abTests.model.ContactCreationForm;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreationForm() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactCreationForm(ContactCreationForm contactCreationForm,  boolean creation) {
        typeValue(By.name("firstname"), contactCreationForm.getFirstName());
        typeValue(By.name("middlename"), contactCreationForm.getMiddleName());
        typeValue(By.name("lastname"), contactCreationForm.getLastName());
        typeValue(By.name("nickname"), contactCreationForm.getNickName());
        typeValue(By.name("byear"), contactCreationForm.getBYear());

        if (creation) {
            if (!wd.findElement(By.name("new_group")).getAttribute("value").equals("[none]")) {
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactCreationForm.getGroup());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

        typeValue(By.name("company"), contactCreationForm.getCompanyName());
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

    public void selectContact() {
        click(By.name("selected[]"));
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

    public void createContact(ContactCreationForm contact) {
        initContactCreation();
        fillContactCreationForm(contact, true);
        submitContactCreationForm();
        returnToMainPage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }
}
