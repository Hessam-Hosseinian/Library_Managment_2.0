package user;

/**
 * The Staff class represents a staff user in the system.
 * It extends the User class and inherits its properties and methods.
 * 
 * @author Hessam Hosseinian
 */
public class Staff extends User {
    /**
     * Constructs a new Staff object with the specified attributes.
     *
     * @param userId     The unique identifier of the staff user.
     * @param password   The password of the staff user.
     * @param firstName  The first name of the staff user.
     * @param lastName   The last name of the staff user.
     * @param nationalId The national ID of the staff user.
     * @param birthDay   The birth date of the staff user.
     * @param address    The address of the staff user.
     */
    public Staff(String userId, String password, String firstName, String lastName, String nationalId, String birthDay,
            String address) {
        super(userId, password, firstName, lastName, nationalId, birthDay, address);

    }

}
