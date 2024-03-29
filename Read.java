import java.sql.Date;

/**
 * The Read class represents a record of a user reading a book in the library
 * system.
 * 
 * @author Hessam Hosseinian
 */
public class Read {

    private String userId;
    private String libraryId;
    private String bookId;
    private Date date;

    /**
     * Constructs a new Read object with the specified attributes.
     *
     * @param userId    The unique identifier of the user who read the book.
     * @param libraryId The ID of the library where the reading occurred.
     * @param bookId    The unique identifier of the book being read.
     * @param date      The date when the reading occurred.
     */
    public Read(String userId, String libraryId, String bookId, Date date) {
        this.userId = userId;
        this.libraryId = libraryId;
        this.bookId = bookId;
        this.date = date;

    }

    // !------------------------------------------USER_ID
    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    // !------------------------------------------LIBRARY_ID

    public String getLibraryId() {
        return this.libraryId;
    }

    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
    }
    // !------------------------------------------BOOK_ID

    public String getBookId() {
        return this.bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
    // !------------------------------------------DATE

    public Date getDate1() {
        return this.date;
    }

    public void setDate1(Date date1) {
        this.date = date1;
    }

}