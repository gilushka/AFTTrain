package abTests.model;

import java.util.Objects;

public class ContactData {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(adress, that.adress) && Objects.equals(email, that.email) && Objects.equals(group, that.group) && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, adress, email, group, phoneNumber);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", adress='" + adress + '\'' +
                ", email='" + email + '\'' +
                ", group='" + group + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    private final String firstName;
    private final String lastName;
    private final String adress;
    private final String email;
    private String group;
    private final String phoneNumber;

    public String getGroup() {
        return group;
    }

    public ContactData(String firstName, String lastName, String adress, String email, String group,String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.adress = adress;
        this.email = email;
        this.group = group;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAdress() {
        return adress;
    }

    public String getEMail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
