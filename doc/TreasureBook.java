package doc;

/**
 * The TreasureBook class represents a special book document in a library system
 * that is considered a treasure. It extends the Document class and inherits its
 * properties and methods.
 * 
 * @author Hessam Hosseinian
 */
public class TreasureBook extends Document {

    private String donor;

    /**
     * Constructs a new TreasureBook object with the specified attributes.
     *
     * @param docId           The unique identifier of the treasure book document.
     * @param title           The title of the treasure book.
     * @param author          The author of the treasure book.
     * @param publisher       The publisher of the treasure book.
     * @param publicationYear The publication year of the treasure book.
     * @param donor           The name of the donor who provided the treasure book.
     * @param categoryId      The category ID of the treasure book.
     * @param libraryId       The library ID where the treasure book is located.
     */
    public TreasureBook(String docId, String title, String author, String publisher, String publicationYear,
            String donor, String categoryId, String libraryId) {
        super(docId, title, author, publisher, publicationYear, 1, categoryId, libraryId);

        this.donor = donor;
    }

    // !--------------------------------------------DONOR
    public String getDonor() {
        return this.donor;
    }

    public void setDonor(String donor) {
        this.donor = donor;
    }

}
