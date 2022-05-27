package abTests.tests;

import abTests.model.GroupData;
import abTests.model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Set;

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
    Groups after = app.group().all();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo(before
            .withAdded(group.withGroupId(after.stream().mapToInt((g) -> g.getGroupId()).max().getAsInt()))));
    app.goTo().mainForm();
  }
}
