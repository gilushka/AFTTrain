package abTests.model;

import java.util.Objects;

public class ContactData {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName);
    }

    private int id;

    private final String firstName;
    private final String lastName;
    private final String address;
    private final String email;
    private String group;
    private final String phoneNumber;

    public String getGroup() {
        return group;
    }

    public ContactData(String lastName, String firstName, String address, String email, String group,String phoneNumber) {
        this.id = 0;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.email = email;
        this.group = group;
        this.phoneNumber = phoneNumber;
    }

    public ContactData(int id, String lastName, String firstName, String address, String email, String group,String phoneNumber) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.email = email;
        this.group = group;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", group='" + group + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
