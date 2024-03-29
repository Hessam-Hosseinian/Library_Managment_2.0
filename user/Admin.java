package user;

/**
 * The Admin class represents an administrator user in the system.
 * It extends the User class and inherits its properties and methods.
 * 
 * @author Hessam Hosseinian
 */
public class Admin extends User {
    /**
     * Constructs a new Admin object with the specified attributes.
     *
     * @param userId     The unique identifier of the admin user.
     * @param password   The password of the admin user.
     * @param firstName  The first name of the admin user.
     * @param lastName   The last name of the admin user.
     * @param nationalId The national ID of the admin user.
     * @param birthDay   The birth date of the admin user.
     * @param address    The address of the admin user.
     */
    public Admin(String userId, String password, String firstName, String lastName, String nationalId, String birthDay,
            String address) {
        super(userId, password, firstName, lastName, nationalId, birthDay, address);

    }

}
