package user;

/**
 * The User class represents a user in the system.
 * 
 * @author Hessam Hosseinan
 */
public class User {
    private String userId;
    private String password;
    private String firstName;
    private String lastName;
    private String nationalId;
    private String birthDay;
    private String address;
    private int debt;

    /**
     * Constructs a new User object with the specified attributes.
     *
     * @param userId     The unique identifier of the user.
     * @param password   The password of the user.
     * @param firstName  The first name of the user.
     * @param lastName   The last name of the user.
     * @param nationalId The national ID of the user.
     * @param birthDay   The birth date of the user.
     * @param address    The address of the user.
     */
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

    // !-------------------------------------------USER_ID
    public String getUserId() {
        return this.userId;
    }

    public void setId(String userId) {
        this.userId = userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    // !-------------------------------------------PASSWORD

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    // !-------------------------------------------FIRST_NAME

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    // !-------------------------------------------LAST_NAME

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    // !-------------------------------------------NATION_ID

    public String getNationalId() {
        return this.nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }
    // !-------------------------------------------BIRTHDAY

    public String getBirthDay() {
        return this.birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }
    // !-------------------------------------------ADDRESS

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // !-------------------------------------------DEBT

    public int getDebt() {
        return this.debt;
    }

    public void setDebt(int debt) {
        this.debt += debt;
    }

}
