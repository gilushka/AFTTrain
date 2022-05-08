package abTests.appmanager;

import abTests.model.GroupCreationForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void submitGroupCreationForm() {
        click(By.name("submit"));
    }

    public void fillGroupCreationForm(GroupCreationForm groupCreationForm) {
        typeValue(By.name("group_name"), groupCreationForm.getGroupName());
        typeValue(By.name("group_header"), groupCreationForm.getGroupHeader());
        typeValue(By.name("group_footer"), groupCreationForm.getGroupFooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void deleteGroup() {
        click(By.name("delete"));
    }

    public void selectGroup() {
        click(By.name("selected[]"));
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void createGroup(GroupCreationForm group) {
        initGroupCreation();
        fillGroupCreationForm(group);
        submitGroupCreationForm();
        returnToGroupPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }
}
