package abTests;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    private WebDriver wd;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
      wd = new ChromeDriver();
      wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      wd.get("http://localhost/addressbook/");
      authorize("admin", "secret");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
      wd.quit();
    }

    private void authorize(String login, String password) {
      wd.findElement(By.name("user")).click();
      wd.findElement(By.name("user")).clear();
      wd.findElement(By.name("user")).sendKeys(login);
      wd.findElement(By.name("pass")).click();
      wd.findElement(By.name("pass")).clear();
      wd.findElement(By.name("pass")).sendKeys(password);
      wd.findElement(By.xpath("//input[@value='Login']")).click();
    }

    protected void submitGroupCreationForm() {
      wd.findElement(By.name("submit")).click();
    }

    protected void fillGroupCreationForm(GroupCreationForm groupCreationForm) {
      wd.findElement(By.name("group_name")).click();
      wd.findElement(By.name("group_name")).clear();
      wd.findElement(By.name("group_name")).sendKeys(groupCreationForm.getGroupName());
      wd.findElement(By.name("group_header")).click();
      wd.findElement(By.name("group_header")).clear();
      wd.findElement(By.name("group_header")).sendKeys(groupCreationForm.getGroupHeader());
      wd.findElement(By.name("group_footer")).click();
      wd.findElement(By.name("group_footer")).clear();
      wd.findElement(By.name("group_footer")).sendKeys(groupCreationForm.getGroupFooter());
    }

    protected void initGroupCreation() {
      wd.findElement(By.name("new")).click();
    }

    protected void gotoGroupPage() {
      wd.findElement(By.linkText("groups")).click();
    }

    protected void logout() {
      wd.findElement(By.linkText("Logout")).click();
    }

    protected void returnToMainForm() {
      wd.findElement(By.linkText("home")).click();
    }

    protected void submitContactCreationForm() {
      wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    protected void fillContactCreationForm(ContactCreationForm contactCreationForm) {
      wd.findElement(By.name("firstname")).click();
      wd.findElement(By.name("firstname")).clear();
      wd.findElement(By.name("firstname")).sendKeys(contactCreationForm.getFirstName());
      wd.findElement(By.name("middlename")).click();
      wd.findElement(By.name("middlename")).clear();
      wd.findElement(By.name("middlename")).sendKeys(contactCreationForm.getMiddlename());
      wd.findElement(By.name("lastname")).click();
      wd.findElement(By.name("lastname")).clear();
      wd.findElement(By.name("lastname")).sendKeys(contactCreationForm.getLastName());
      wd.findElement(By.name("nickname")).click();
      wd.findElement(By.name("nickname")).clear();
      wd.findElement(By.name("nickname")).sendKeys(contactCreationForm.getNickName());
      wd.findElement(By.name("bday")).click();
      new Select(wd.findElement(By.name("bday"))).selectByVisibleText(contactCreationForm.getbDay());
      wd.findElement(By.name("bmonth")).click();
      new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contactCreationForm.getbMonth());
      wd.findElement(By.name("byear")).click();
      wd.findElement(By.name("byear")).clear();
      wd.findElement(By.name("byear")).sendKeys(contactCreationForm.getbYear());
      wd.findElement(By.name("company")).click();
      wd.findElement(By.name("company")).clear();
      wd.findElement(By.name("company")).sendKeys(contactCreationForm.getCompanyName());
      wd.findElement(By.name("mobile")).click();
      wd.findElement(By.name("mobile")).clear();
      wd.findElement(By.name("mobile")).sendKeys(contactCreationForm.getPhoneNumber());
    }

    protected void initContactCreation() {
      wd.findElement(By.linkText("add new")).click();
    }

    protected void returnToGroupPage() {
        wd.findElement(By.linkText("group page")).click();
    }

    protected void deleteGroup() {
        wd.findElement(By.name("delete")).click();
    }

    protected void selectGroup() {
        wd.findElement(By.name("selected[]")).click();
    }
}
