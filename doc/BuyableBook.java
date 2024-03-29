package doc;

/**
 * The BuyableBook class represents a book document that can be purchased in a
 * library system.
 * It extends the Document class and inherits its properties and methods.
 * 
 * @author Hessam Hosseinian
 */
public class BuyableBook extends Document {

    private int price;
    private int discountAmount;

    /**
     * Constructs a new BuyableBook object with the specified attributes.
     *
     * @param docId           The unique identifier of the buyable book document.
     * @param title           The title of the buyable book.
     * @param author          The author of the buyable book.
     * @param publisher       The publisher of the buyable book.
     * @param publicationYear The publication year of the buyable book.
     * @param copyNumber      The copy number of the buyable book.
     * @param price           The price of the buyable book.
     * @param discountAmount  The discount amount applied to the buyable book.
     * @param categoryId      The category ID of the buyable book.
     * @param libraryId       The library ID where the buyable book is located.
     */
    public BuyableBook(String docId, String title, String author, String publisher, String publicationYear,
            int copyNumber, int price, int discountAmount, String categoryId, String libraryId) {
        super(docId, title, author, publisher, publicationYear, copyNumber, categoryId, libraryId);
        this.price = price;
        this.discountAmount = discountAmount;

    }

    // ?-----------------------------------------------------------------------------------------------------------
    /**
     * Calculates the discounted price of the buyable book.
     *
     * @return The discounted price of the buyable book.
     */
    public int calculatePrice() {

        return (int) Math.floor(this.price * (100.0 - this.discountAmount) / 100.0);

    }

    // !--------------------------------------------------------- PRICE
    public int getPrice() {
        return this.price;
    }

    // ?---------------------------------------------
    public void setPrice(int price) {
        this.price = price;
    }

    // !--------------------------------------------------------- DISCOUNT_AMOUNT
    public int getDiscountAmount() {
        return this.discountAmount;
    }
    // ?---------------------------------------------

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }
    // ?---------------------------------------------

}
