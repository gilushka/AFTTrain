package abTests.tests;

import abTests.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class ModificateGroupTest extends BaseTest {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 1) {
            app.group().create(new GroupData().withGroupName("Снурфики"));
        }
    }

    @Test
    public void testGroupModification() {
        Set<GroupData> before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withGroupId(modifiedGroup.getGroupId())
                .withGroupName("Снурфики")
                .withGroupHeader("New Snurfics")
                .withGroupFooter("Сотрудники компании Новые Снурфики");
        app.group().modify(group);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedGroup);
        before.add(group);

        Assert.assertEquals(before, after);
        app.goTo().mainForm();
    }
}
