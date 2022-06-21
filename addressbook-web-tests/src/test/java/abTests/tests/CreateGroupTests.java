package abTests.tests;

import abTests.model.GroupData;
import abTests.model.Groups;
import org.testng.annotations.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateGroupTests extends BaseTest {

  @Test
  public void testGroupCreate() throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData()
            .withGroupName("Снурфики")
            .withGroupHeader("Snurfics")
            .withGroupFooter("Сотрудники компании Снурфики");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.group().all();

    assertThat(after, equalTo(before
            .withAdded(group.withGroupId(after.stream().mapToInt((g) -> g.getGroupId()).max().getAsInt()))));
    app.goTo().mainForm();
  }

  @Test
  public void testBadGroupCreate() throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData()
            .withGroupName("Снурфики'")
            .withGroupHeader("Snurfics")
            .withGroupFooter("Сотрудники компании Снурфики");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();

    assertThat(after, equalTo(before));
    app.goTo().mainForm();
  }
}
