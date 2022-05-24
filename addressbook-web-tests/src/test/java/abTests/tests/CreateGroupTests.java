package abTests.tests;

import abTests.model.ContactData;
import abTests.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.List;

public class CreateGroupTests extends BaseTest {

  @Test
  public void testGroupCreate() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    GroupData group = new GroupData().withGroupName("Снурфики").withGroupHeader("Snurfics").withGroupFooter("Сотрудники компании Снурфики");
    app.getGroupHelper().createGroup(group);
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getGroupId(), g2.getGroupId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
    app.getNavigationHelper().returnToMainForm();
  }
}
