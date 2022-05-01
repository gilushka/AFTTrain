package abTests.appmanager;

import abTests.model.ContactCreationForm;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreationForm() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactCreationForm(ContactCreationForm contactCreationForm) {
        typeValue(By.name("firstname"), contactCreationForm.getFirstName());
        typeValue(By.name("middlename"), contactCreationForm.getMiddlename());
        typeValue(By.name("lastname"), contactCreationForm.getLastName());
        typeValue(By.name("nickname"), contactCreationForm.getNickName());
        click(By.name("bday"));
        new Select(wd.findElement(By.name("bday"))).selectByVisibleText(contactCreationForm.getbDay());
        click(By.name("bmonth"));
        new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contactCreationForm.getbMonth());
        typeValue(By.name("byear"), contactCreationForm.getbYear());
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
}
