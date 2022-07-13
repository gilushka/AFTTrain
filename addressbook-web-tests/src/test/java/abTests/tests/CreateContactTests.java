package abTests.tests;

import abTests.model.ContactData;
import abTests.model.Contacts;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateContactTests extends BaseTest {
  private Properties properties;

  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(app.getProperties().getProperty("testData.contactXml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xStream = new XStream();
      xStream.allowTypes(new Class[]{ContactData.class});
      xStream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
      return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(app.getProperties().getProperty("testData.contactJson")))){
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {}.getType()); //Эта конструкция имеет смысл List<GroupData>.class
      return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validContactsFromJson")
  public void testContactCreate(ContactData contact) throws Exception {
    Contacts before = app.db().contacts();
    File photo = new File("src/test/resources/photo1.png");
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.db().contacts();

    assertThat(after, equalTo(before
            .withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
