import java.sql.Date;

public class Read {

    private String userId;
    private String libraryId;
    private String bookId;

    private Date date1;
    // private String startDate;
    // private String endDate;

    public Read(String userId, String libraryId, String bookId, Date date1) {
        this.userId = userId;
        this.libraryId = libraryId;
        this.bookId = bookId;
        this.date1 = date1;

    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLibraryId() {
        return this.libraryId;
    }

    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
    }

    public String getBookId() {
        return this.bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Date getDate1() {
        return this.date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

}