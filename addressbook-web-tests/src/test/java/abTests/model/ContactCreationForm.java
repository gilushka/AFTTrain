package abTests.model;

public class ContactCreationForm {
    private final String firstName;
    private final String middlename;
    private final String lastName;
    private final String nickName;
    private final String bDay;
    private final String bMonth;
    private final String bYear;
    private final String companyName;
    private final String phoneNumber;

    public ContactCreationForm(String firstName, String middlename, String lastName, String nickName, String bDay, String bMonth, String bYear, String companyName, String phoneNumber) {
        this.firstName = firstName;
        this.middlename = middlename;
        this.lastName = lastName;
        this.nickName = nickName;
        this.bDay = bDay;
        this.bMonth = bMonth;
        this.bYear = bYear;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getbDay() {
        return bDay;
    }

    public String getbMonth() {
        return bMonth;
    }

    public String getbYear() {
        return bYear;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
