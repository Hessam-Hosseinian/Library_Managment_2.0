package doc;

public class BuyableBook extends Document {

    public BuyableBook(String docId, String title, String author, String publisher, String publicationYear,
            int copyNumber, int price, int discountAmount, String categoryId, String libraryId) {
        super(docId, title, author, publisher, publicationYear, copyNumber, categoryId, libraryId);
        this.price = price;
        this.discountAmount = discountAmount;
        this.availableCopyNumber = copyNumber;
    }

    private int price;
    private int discountAmount;
    private int availableCopyNumber;

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscountAmount() {
        return this.discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public int getAvailableCopyNumber() {
        return this.availableCopyNumber;
    }

    public void setAvailableCopyNumber() {
        this.availableCopyNumber--;
    }

}
