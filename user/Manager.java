package user;

public class Manager extends User {

    public Manager(String userId, String password, String firstName, String lastName, String nationalId,
            String birthDay, String address, String libraryId) {
        super(userId, password, firstName, lastName, nationalId, birthDay, address);
        this.libraryId = libraryId;
    }

    private String libraryId;

    public String getLibraryId() {
        return this.libraryId;
    }

    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
    }

}
