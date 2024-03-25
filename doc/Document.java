package doc;

import java.util.ArrayList;

public class Document {

    private String docId;
    private String title;
    private String author;
    private String publisher;
    private String publicationYear;
    private int copyNumber;
    private String categoryId;
    private String libraryId;
    private int availableCopyNumber;
    private ArrayList<String> comments;

    public Document(String docId, String title, String author, String publisher, String publicationYear, int copyNumber,
            String categoryId, String libraryId) {
        this.docId = docId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.copyNumber = copyNumber;
        this.categoryId = categoryId;
        this.libraryId = libraryId;
        this.availableCopyNumber = copyNumber;
        this.comments = new ArrayList<>();

    }

    public void addComment(String comment) {
        comments.add(comment);

    }

    public String getDocId() {
        return this.docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublicationYear() {
        return this.publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getCopyNumber() {
        return this.copyNumber;
    }

    public void setCopyNumber(int copyNumber) {
        this.copyNumber = copyNumber;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getLibraryId() {
        return this.libraryId;
    }

    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
    }

    public int getAvailableCopyNumber() {
        return this.availableCopyNumber;
    }

    public void setAvailableCopyNumber(int availableCopyNumber) {
        this.availableCopyNumber = availableCopyNumber;
    }

    public void decreaseAvailableCopyNumber() {

        this.availableCopyNumber--;
    }

    public void increaseAvailableCopyNumber() {
        this.availableCopyNumber++;
    }

    public ArrayList<String> getComments() {
        return this.comments;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

}
