package abTests.tests;

import abTests.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Set;

public class CreateGroupTests extends BaseTest {

  @Test
  public void testGroupCreate() throws Exception {
    app.goTo().groupPage();
    Set<GroupData> before = app.group().all();
    GroupData group = new GroupData().withGroupName("Снурфики").withGroupHeader("Snurfics").withGroupFooter("Сотрудники компании Снурфики");
    app.group().create(group);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    group.withGroupId(after.stream().mapToInt((g) -> g.getGroupId()).max().getAsInt());
    before.add(group);
    Assert.assertEquals(before, after);
    app.goTo().mainForm();
  }
}
