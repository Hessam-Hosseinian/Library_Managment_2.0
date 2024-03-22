package doc;

public class BuyableBook extends Document {

    public BuyableBook(String docId, String title, String author, String publisher, String publicationYear,
            int copyNumber, int price, int discountAmount, String categoryId, String libraryId) {
        super(docId, title, author, publisher, publicationYear, copyNumber, categoryId, libraryId);
        this.price = price;
        this.discountAmount = discountAmount;
    }

    private int price;
    private int discountAmount;

}
