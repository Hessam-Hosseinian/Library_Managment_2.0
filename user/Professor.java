package user;

/**
 * The Professor class represents a professor user in the system.
 * It extends the User class and inherits its properties and methods.
 * 
 * @author Hessam Hosseinian
 */
public class Professor extends User {

    /**
     * Constructs a new Professor object with the specified attributes.
     *
     * @param userId     The unique identifier of the professor user.
     * @param password   The password of the professor user.
     * @param firstName  The first name of the professor user.
     * @param lastName   The last name of the professor user.
     * @param nationalId The national ID of the professor user.
     * @param birthDay   The birth date of the professor user.
     * @param address    The address of the professor user.
     */
    public Professor(String userId, String password, String firstName, String lastName, String nationalId,
            String birthDay, String address) {
        super(userId, password, firstName, lastName, nationalId, birthDay, address);

    }

}
