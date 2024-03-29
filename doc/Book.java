package doc;

/**
 * The Book class represents a book document in a library system.
 * It extends the Document class and inherits its properties and methods.
 * 
 * @author Hessam Hosseinian
 */
public class Book extends Document {

    /**
     * Constructs a new Book object with the specified attributes.
     *
     * @param docId           The unique identifier of the book document.
     * @param title           The title of the book.
     * @param author          The author of the book.
     * @param publisher       The publisher of the book.
     * @param publicationYear The publication year of the book.
     * @param copyNumber      The copy number of the book.
     * @param categoryId      The category ID of the book.
     * @param libraryId       The library ID where the book is located.
     * @author Hessam Hosseinan
     */
    public Book(String docId, String title, String author, String publisher, String publicationYear, int copyNumber,
            String categoryId, String libraryId) {
        super(docId, title, author, publisher, publicationYear, copyNumber, categoryId, libraryId);

    }

}
