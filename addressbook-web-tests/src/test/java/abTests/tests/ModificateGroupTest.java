package abTests.tests;

import abTests.model.ContactData;
import abTests.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ModificateGroupTest extends BaseTest {

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData().withGroupName("Снурфики"));
        }
    }

    @Test
    public void testGroupModification() {
        List<GroupData> before = app.getGroupHelper().getGroupList();
        int index = before.size() - 1;
        GroupData group = new GroupData()
                .withGroupId(before.get(index).getGroupId())
                .withGroupName("Снурфики")
                .withGroupHeader("New Snurfics")
                .withGroupFooter("Сотрудники компании Новые Снурфики");
        app.getGroupHelper().modifyGroup(group, index);
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getGroupId(), g2.getGroupId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
        app.getNavigationHelper().returnToMainForm();
    }
}
