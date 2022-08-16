package abTests.tests;

import abTests.model.ContactData;
import abTests.model.GroupData;
import abTests.model.Groups;
import com.google.gson.Gson;
//import com.sun.org.slf4j.internal.Logger;
//import com.sun.org.slf4j.internal.LoggerFactory;
import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateGroupTests extends BaseTest {

//  Logger logger = LoggerFactory.getLogger(CreateGroupTests.class);
//  private Properties properties;

  @DataProvider
  public Iterator<Object[]> validGroupsFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(app.getProperties().getProperty("testData.groupsXml")))){
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xStream = new XStream();
      xStream.allowTypes(new Class[]{GroupData.class});
      xStream.processAnnotations(GroupData.class);
      List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml);
      return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

  }

  @DataProvider
  public Iterator<Object[]> validGroupsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(app.getProperties().getProperty("testData.groupsJson")))){
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {}.getType()); //Эта конструкция имеет смысл List<GroupData>.class
      return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

//  @Test(dataProvider = "validGroupsFromJson")
//  public void testGroupCreate(GroupData group){
  @Test
  public void testGroupCreate(){
    app.goTo().groupPage();
    Groups before = app.db().groups();
    GroupData group = new GroupData()
            .withGroupName("Пухлики")
            .withGroupHeader("Пухляши")
            .withGroupFooter("Пухтышки");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.db().groups();

    assertThat(after, equalTo(before
            .withAdded(group.withGroupId(after.stream().mapToInt((g) -> g.getGroupId()).max().getAsInt()))));
    verifyGroupListInUI();
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
