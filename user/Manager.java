package user;

/**
 * The Manager class represents a manager user in the system.
 * It extends the User class and inherits its properties and methods.
 * 
 * @author Hessam Hosseinian
 */
public class Manager extends User {

    private String libraryId;

    /**
     * Constructs a new Manager object with the specified attributes.
     *
     * @param userId     The unique identifier of the manager user.
     * @param password   The password of the manager user.
     * @param firstName  The first name of the manager user.
     * @param lastName   The last name of the manager user.
     * @param nationalId The national ID of the manager user.
     * @param birthDay   The birth date of the manager user.
     * @param address    The address of the manager user.
     * @param libraryId  The ID of the library managed by the manager.
     */
    public Manager(String userId, String password, String firstName, String lastName, String nationalId,
            String birthDay, String address, String libraryId) {
        super(userId, password, firstName, lastName, nationalId, birthDay, address);
        this.libraryId = libraryId;
    }

    // !---------------------------------------------LIBRARY_ID
    public String getLibraryId() {
        return this.libraryId;
    }

    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
    }

}
