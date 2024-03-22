import java.util.HashMap;

import user.Manager;
import doc.Book;
import doc.BuyableBook;
import doc.Document;
import doc.Thesis;
import doc.TreasureBook;

public class Library {

    private String libraryId;
    private String libraryName;
    private String foundationYear;
    private int deskNumber;
    private String address;

    // private HashMap<String, Manager> managers;

    private HashMap<String, Document> documents;

    public Library(String libraryId, String libraryName, String foundationYear, int deskNumber, String address) {
        this.libraryId = libraryId;
        this.libraryName = libraryName;
        this.foundationYear = foundationYear;
        this.deskNumber = deskNumber;
        this.address = address;

        this.documents = new HashMap<>();

    }

    public void addBook(Book book) {

        documents.put(book.getDocId(), book);

    }

    public void addThesis(Thesis thesis) {

        documents.put(thesis.getDocId(), thesis);

    }

    public void addTreasureBook(TreasureBook treasureBook) {

        documents.put(treasureBook.getDocId(), treasureBook);

    }

    public void addSellingBook(BuyableBook buyableBook) {

        documents.put(buyableBook.getDocId(), buyableBook);

    }

    public String getLibraryId() {
        return this.libraryId;
    }

    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
    }

    public String getLibraryName() {
        return this.libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getFoundationYear() {
        return this.foundationYear;
    }

    public void setFoundationYear(String foundationYear) {
        this.foundationYear = foundationYear;
    }

    public int getDeskNumber() {
        return this.deskNumber;
    }

    public void setDeskNumber(int deskNumber) {
        this.deskNumber = deskNumber;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // public HashMap<String, Manager> getManagers() {
    // return this.managers;
    // }

    // public void setManagers(HashMap<String, Manager> managers) {
    // this.managers = managers;
    // }

    public Document getDocuments(String Id) {
        return documents.get(Id);

    }

    public void setDocuments(HashMap<String, Document> documents) {
        this.documents = documents;
    }

    public void res() {

        System.out.println(documents.size() + " documents");

    }
}
