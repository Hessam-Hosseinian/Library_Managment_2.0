package doc;

public class TreasureBook extends Document {

    public TreasureBook(String docId, String title, String author, String publisher, String publicationYear,
            String donor, String categoryId, String libraryId) {
        super(docId, title, author, publisher, publicationYear, 1, categoryId, libraryId);

        this.donor = donor;
    }

    private String donor;

    public String getDonor() {
        return this.donor;
    }

    public void setDonor(String donor) {
        this.donor = donor;
    }

}
