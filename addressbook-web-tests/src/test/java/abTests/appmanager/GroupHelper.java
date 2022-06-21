package abTests.appmanager;

import abTests.model.GroupData;
import abTests.model.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void submitGroupCreationForm() {
        click(By.name("submit"));
    }

    public void fillGroupCreationForm(GroupData groupCreationForm) {
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

    public void selectGroupById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupCreationForm(group);
        submitGroupCreationForm();
        groupCache = null;
        returnToGroupPage();
    }

    public void modify(GroupData group) {
        selectGroupById(group.getGroupId());
        initGroupModification();
        fillGroupCreationForm(group);
        submitGroupModification();
        groupCache = null;
        returnToGroupPage();
    }

    public void delete(GroupData group) {
        selectGroupById(group.getGroupId());
        deleteGroup();
        groupCache = null;
        returnToGroupPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Groups groupCache = null;

    public Groups all() {
        if (groupCache != null) {
            return new Groups(groupCache);
        }

        groupCache = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element: elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupCache.add(new GroupData().withGroupId(id).withGroupName(name));
        }
        return new Groups(groupCache);
    }
}
