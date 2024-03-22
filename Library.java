import java.util.ArrayList;
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

    private HashMap<String, ArrayList<Borrow>> borrows;

    public Library(String libraryId, String libraryName, String foundationYear, int deskNumber, String address) {
        this.libraryId = libraryId;
        this.libraryName = libraryName;
        this.foundationYear = foundationYear;
        this.deskNumber = deskNumber;
        this.address = address;

        this.documents = new HashMap<>();
        this.borrows = new HashMap<>();
    }

    public boolean checkDocument(String docId) {

        if (documents.get(docId) == null) {
            return true;

        }
        return false;

    }

    public int countBorrows(String userId) {
        int count = 0;
        for (ArrayList<Borrow> docBorrows : new ArrayList<>(borrows.values())) {
            for (Borrow borrow : docBorrows) {
                if (borrow.getUserId().equals(userId)) {
                    count++;
                }
            }
        }
        return count;
    }

    public int countDocs(String docId) {
        ArrayList<Borrow> myBorrow = borrows.get(docId);
        if (myBorrow == null) {
            return 0;
        }
        return myBorrow.size();
    }

    public boolean borrow(Borrow borrow, int userBorrow) {
        if (borrow.isStudent()) {
            if (userBorrow < 3) {
                if (isAllowed(borrow)) {
                    return true;
                }
            }
        } else {
            if (userBorrow < 5) {
                if (isAllowed(borrow)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isAllowed(Borrow borrow) {
        ArrayList<Borrow> borrows1 = borrows.get(borrow.getDocumentId());
        if (borrows1 == null) {
            borrows1 = new ArrayList<>();

        }
        if (borrow.isBook()) {
            if (countDocs(borrow.getDocumentId()) < documents.get(borrow.getDocumentId()).getCopyNumber()) {
                borrows1.add(borrow);
                borrows.put(borrow.getDocumentId(), borrows1);
                return true;
            }
            return false;
        }
        if (countDocs(borrow.getDocumentId()) == 0) {
            borrows1.add(borrow);
            borrows.put(borrow.getDocumentId(), borrows1);
            return true;
        }
        return false;
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

    public Boolean removeResource(String docId) {

        if (documents.get(docId) != null) {

            documents.remove(docId);
            return true;
        }
        return false;

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
