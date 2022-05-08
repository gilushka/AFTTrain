package abTests.model;

public class ContactCreationForm {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String nickName;
    private final String bYear;
    private String group;
    private final String companyName;
    private final String phoneNumber;

    public String getGroup() {
        return group;
    }

    public ContactCreationForm(String firstName, String middleName, String lastName, String nickName, String bYear, String group, String companyName, String phoneNumber) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.bYear = bYear;
        this.group = group;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getBYear() {
        return bYear;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
