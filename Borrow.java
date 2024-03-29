import java.util.Date;

/**
 * The Borrow class represents a borrowing transaction in the library system.
 * 
 * @author Hessam Hosseinian
 */
public class Borrow {

    private Date date;
    private String userId;
    private String documentId;
    private String libraryId;
    private boolean isStudent;
    private boolean isProfessor;
    private boolean isBook;

    /**
     * Constructs a new Borrow object with the specified attributes.
     *
     * @param date       The date of the borrowing transaction.
     * @param userId     The unique identifier of the user who borrowed the
     *                   document.
     * @param documentId The unique identifier of the document being borrowed.
     * @param libraryId  The ID of the library where the borrowing transaction
     *                   occurred.
     */
    public Borrow(Date date, String userId, String documentId, String libraryId) {
        this.libraryId = libraryId;
        this.date = date;
        this.userId = userId;
        this.documentId = documentId;
    }
    // ------------------------------------------------------------------------------------------------------------------

    // !-------------------------------------------------DATE

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    // !-------------------------------------------------USER_ID

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    // !-------------------------------------------------DOCUMENT

    public String getDocumentId() {
        return this.documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
    // !-------------------------------------------------LIBRARY_ID

    public String getLibraryId() {
        return this.libraryId;
    }

    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
    }
    // !-------------------------------------------------IS_STUDENT

    public boolean isIsStudent() {
        return this.isStudent;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public boolean getIsStudent() {
        return this.isStudent;
    }

    public void setIsStudent(boolean isStudent) {
        this.isStudent = isStudent;
    }
    // !-------------------------------------------------IS_BOOK

    public boolean isIsBook() {
        return this.isBook;
    }

    public boolean isBook() {
        return isBook;
    }

    public boolean getIsBook() {
        return this.isBook;
    }

    public void setIsBook(boolean isBook) {
        this.isBook = isBook;
    }

    // !-------------------------------------------------PROFESSOR
    public boolean isIsProfessor() {
        return this.isProfessor;
    }

    public boolean getIsProfessor() {
        return this.isProfessor;
    }

    public void setIsProfessor(boolean isProfessor) {
        this.isProfessor = isProfessor;
    }

}
