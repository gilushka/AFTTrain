package abTests.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("group")
@Entity
@Table(name = "group_list")
public class GroupData {

    @XStreamOmitField
    @Id
    @Column(name = "group_id")
    private int groupId = Integer.MAX_VALUE;

    @Expose
    @Column(name = "group_name")
//    @Type(type = "text")
    private String groupName;

    @Expose
    @Column(name = "group_header")
    @Type(type = "text")
    private String groupHeader;

    @Expose
    @Column(name = "group_footer")
    @Type(type = "text")
    private String groupFooter;

    @ManyToMany(mappedBy = "groups")
    private Set<ContactData> contacts = new HashSet<ContactData>();

    public int getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getGroupHeader() {
        return groupHeader;
    }

    public String getGroupFooter() {
        return groupFooter;
    }

    public Contacts getContacts() {
        return new Contacts(contacts);
    }

    public GroupData withGroupId(int groupId) {
        this.groupId = groupId;
        return this;
    }

    public GroupData withGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public GroupData withGroupHeader(String groupHeader) {
        this.groupHeader = groupHeader;
        return this;
    }

    public GroupData withGroupFooter(String groupFooter) {
        this.groupFooter = groupFooter;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupData groupData = (GroupData) o;
        return groupId == groupData.groupId && Objects.equals(groupName, groupData.groupName) && Objects.equals(groupHeader, groupData.groupHeader) && Objects.equals(groupFooter, groupData.groupFooter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, groupName, groupHeader, groupFooter);
    }

    @Override
    public String toString() {
        return "GroupData{" +
                "groupId='" + groupId + '\'' +
                ", groupName='" + groupName + '\'' +
                '}';
    }

}
