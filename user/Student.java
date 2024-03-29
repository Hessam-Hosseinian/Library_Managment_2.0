package user;

/**
 * The Student class represents a student user in the system.
 * It extends the User class and inherits its properties and methods.
 * 
 * @author Hessam Hosseinian
 */
public class Student extends User {
    /**
     * Constructs a new Student object with the specified attributes.
     *
     * @param userId     The unique identifier of the student user.
     * @param password   The password of the student user.
     * @param firstName  The first name of the student user.
     * @param lastName   The last name of the student user.
     * @param nationalId The national ID of the student user.
     * @param birthDay   The birth date of the student user.
     * @param address    The address of the student user.
     */
    public Student(String userId, String password, String firstName, String lastName, String nationalId,
            String birthDay, String address) {
        super(userId, password, firstName, lastName, nationalId, birthDay, address);

    }

}
