package user;

public class User {
    private String userId;
    private String password;
    private String firstName;
    private String lastName;
    private String nationalId;
    private String birthDay;
    private String address;
    private int debt;

    public User(String userId, String password, String firstName, String lastName, String nationalId, String birthDay,
            String address) {
        this.userId = userId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalId = nationalId;
        this.birthDay = birthDay;
        this.address = address;
        this.debt = 0;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationalId() {
        return this.nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getBirthDay() {
        return this.birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getDebt() {
        return this.debt;
    }

    public void setDebt(int debt) {
        this.debt = debt;
    }

}
