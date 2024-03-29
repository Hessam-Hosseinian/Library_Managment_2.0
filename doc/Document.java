package doc;

import java.util.ArrayList;

/**
 * The Document class represents a generic document in a library system.
 * It serves as a superclass for more specific document types like books,
 * theses,buyablebooks and treasurebooks .
 */
public class Document {

    private String docId;
    private String title;
    private String author;
    private String publisher;
    private String publicationYear;
    private String categoryId;
    private String libraryId;
    private int copyNumber;
    private int availableCopyNumber;
    private int countOfBorroewed;
    private int daysOfBorrowed;
    private long timeThatdocIsUnderBorrow;
    private ArrayList<String> comments;

    // ?-----------------------------------------------------------------------------------------------------------
    /**
     * Constructs a new Document object with the specified attributes.
     *
     * @param docId           The unique identifier of the document.
     * @param title           The title of the document.
     * @param author          The author of the document.
     * @param publisher       The publisher of the document.
     * @param publicationYear The publication year of the document.
     * @param copyNumber      The total number of copies of the document.
     * @param categoryId      The category ID of the document.
     * @param libraryId       The library ID where the document is located.
     * 
     */
    public Document(String docId, String title, String author, String publisher, String publicationYear, int copyNumber,
            String categoryId, String libraryId) {
        this.docId = docId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.categoryId = categoryId;
        this.libraryId = libraryId;
        this.copyNumber = copyNumber;
        this.availableCopyNumber = copyNumber;
        this.countOfBorroewed = 0;
        this.daysOfBorrowed = 0;
        this.timeThatdocIsUnderBorrow = 0;
        this.comments = new ArrayList<>();

    }
    // ?-----------------------------------------------------------------------------------------------------------

    // !----------------------------------------------------DOC_ID

    public String getDocId() {
        return this.docId;
    }
    // ?----------------------------------------------------

    public void setDocId(String docId) {
        this.docId = docId;
    }
    // !----------------------------------------------------TITLE

    public String getTitle() {
        return this.title;
    }
    // ?----------------------------------------------------

    public void setTitle(String title) {
        this.title = title;
    }
    // !----------------------------------------------------AUTHOR

    public String getAuthor() {
        return this.author;
    }
    // ?----------------------------------------------------

    public void setAuthor(String author) {
        this.author = author;
    }
    // !----------------------------------------------------PUBLISHER

    public String getPublisher() {
        return this.publisher;
    }
    // ?----------------------------------------------------

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    // !----------------------------------------------------PUBLICATION_YEAR

    public String getPublicationYear() {
        return this.publicationYear;
    }
    // ?----------------------------------------------------

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }
    // !----------------------------------------------------CATEGORY_ID

    public String getCategoryId() {
        return this.categoryId;
    }
    // ?----------------------------------------------------

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    // !----------------------------------------------------LIBRARY_ID

    public String getLibraryId() {
        return this.libraryId;
    }
    // ?----------------------------------------------------

    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
    }
    // !----------------------------------------------------COPY_NUMBER

    public int getCopyNumber() {
        return this.copyNumber;
    }
    // ?----------------------------------------------------

    public void setCopyNumber(int copyNumber) {
        this.copyNumber = copyNumber;
    }
    // !----------------------------------------------------AVAILABLE_COPY_NUMBER

    public int getAvailableCopyNumber() {
        return this.availableCopyNumber;
    }
    // ?----------------------------------------------------

    public void setAvailableCopyNumber(int availableCopyNumber) {
        this.availableCopyNumber = availableCopyNumber;
    }
    // ?----------------------------------------------------

    public void decreaseAvailableCopyNumber() {

        this.availableCopyNumber--;
    }
    // ?----------------------------------------------------

    public void increaseAvailableCopyNumber() {
        this.availableCopyNumber++;
    }
    // !----------------------------------------------------COUNT_OF_BORROWED

    public int getCountOfBorroewed() {
        return this.countOfBorroewed;
    }
    // ?----------------------------------------------------

    public void setCountOfBorroewed(int countOfBorroewed) {
        this.countOfBorroewed = countOfBorroewed;
    }
    // ?----------------------------------------------------

    public void increaseCountOfBorrowed() {
        countOfBorroewed++;
    }
    // !----------------------------------------------------DAYS_OF_BORROWED

    public int getDaysOfBorrowed() {
        return this.daysOfBorrowed;
    }
    // ?----------------------------------------------------

    public void setDaysOfBorrowed(int daysOfBorrowed) {
        this.daysOfBorrowed += daysOfBorrowed;
    }
    // !----------------------------------------------------TIME_THAT_DOC_IS_UNDER_BORROW

    public long getTimeThatdocIsUnderBorrow() {
        return this.timeThatdocIsUnderBorrow;
    }
    // ?----------------------------------------------------

    public void setTimeThatdocIsUnderBorrow(long timeThatdocIsUnderBorrow) {
        this.timeThatdocIsUnderBorrow += timeThatdocIsUnderBorrow;
    }

    // !---------------------------------------------------- COMMENT

    public ArrayList<String> getComments() {
        return this.comments;
    }
    // ?----------------------------------------------------

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }
    // ?----------------------------------------------------

    public void addComment(String comment) {
        this.comments.add(comment);

    }
    // ?----------------------------------------------------

}
