import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.xml.crypto.Data;

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

    private HashMap<String, Read> reads;

    public Library(String libraryId, String libraryName, String foundationYear, int deskNumber, String address) {
        this.libraryId = libraryId;
        this.libraryName = libraryName;
        this.foundationYear = foundationYear;
        this.deskNumber = deskNumber;
        this.address = address;

        this.documents = new HashMap<>();
        this.borrows = new HashMap<>();
        this.reads = new HashMap<>();
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

    public boolean checkdoublacheck(String userId, String DocumentId) {

        for (ArrayList<Borrow> docBorrows : new ArrayList<>(borrows.values())) {
            for (Borrow borrow : docBorrows) {
                if (borrow.getUserId().equals(userId) && borrow.getDocumentId().equals(DocumentId)) {
                    return true;
                }
            }
        }
        return false;

    }

    public boolean checkAvailabilityBorrow(String docId) {

        Document doc = documents.get(docId);
        if (doc instanceof BuyableBook || doc instanceof TreasureBook) {
            return true;
        }

        return false;

    }

    public boolean isIsBook(String docId) {
        Document doc = documents.get(docId);
        if (doc instanceof Book) {
            return true;

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

    public Borrow checkUserBorrows(String userId, String docId) {
        Borrow borrowHelp = null;
        ArrayList<Borrow> hold = borrows.get(docId);
        if (hold == null) {

            return null;
        }
        for (Borrow borrow : hold) {
            if (borrow.getUserId().equals(userId)) {

                borrowHelp = borrow;

                return borrowHelp;
            }
        }
        return borrowHelp;
    }

    public int returning(Borrow borrow, java.util.Date date) {
        ArrayList<Borrow> borrows1 = borrows.get(borrow.getDocumentId());
        int debt = checkDebt(borrow, date);
        borrows1.remove(borrow);
        return debt;
    }

    public Boolean checkdebtFor(String userId, Date date) {
        for (ArrayList<Borrow> docBorrows : new ArrayList<>(borrows.values())) {
            for (Borrow borrow : docBorrows) {
                if (checkDebt(borrow, date) != 0 && borrow.getUserId().equals(userId)) {
                    return true;
                }
            }
        }
        return false;

    }

    public int checkDebt(Borrow borrow, Date date) {

        long firstMin = borrow.getDate().getTime() / 3600000; // getTime return time as millisecond

        long secondMin = ((Date) date).getTime() / 3600000; // getTime return time as millisecond

        long periodTime = secondMin - firstMin;

        if (borrow.isStudent()) {
            if (borrow.isBook()) {
                if (periodTime < (10 * 24)) {
                    return 0;
                }
                return (int) ((periodTime - (10 * 24)) * 50); // BOOK AND STUDENT
            }
            if (periodTime < (7 * 24)) {
                return 0;
            }
            return (int) ((periodTime - (7 * 24)) * 50); // THESIS AND STUDENT
        }
        if (borrow.isBook()) {
            if (periodTime < (14 * 24)) {
                return 0;
            }
            return (int) ((periodTime - (14 * 24)) * 100); // BOOK AND STAFF
        }
        if (periodTime < (10 * 24)) {
            return 0;
        }
        return (int) ((periodTime - (10 * 24)) * 100);// THESIS AND STAFF
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

    public boolean buyBook(String documentID) {
        Document document = documents.get(documentID);
        if (document == null) {
            System.out.println("not-found");
            return false;

        }
        if (!(document instanceof BuyableBook)) {

            System.out.println("not-allowed");
            return false;
        }
        BuyableBook buyableBook = (BuyableBook) document;

        if (buyableBook.getAvailableCopyNumber() == 0) {

            System.out.println("not-allowed");
            return false;
        }
        buyableBook.setAvailableCopyNumber();
        return true;

    }

    public boolean readBook(Read read) {
        Document document = documents.get(read.getBookId());
        if (document == null) {

            System.out.println("not-found");
            return false;

        }
        if (!(document instanceof TreasureBook)) {

            System.out.println("not-allowed");
            return false;
        }
        // TreasureBook treasureBook = (TreasureBook) document;

        for (Read read2 : reads.values()) {

            if (read2.getBookId().equals(read.getBookId())
                    && read2.getDate1().getTime() == read.getDate1().getTime() && conflict(read, read2)) {
                System.out.println("not-allowed");
                return false;
            }

        }

        reads.put(read.getBookId(), read);

        return true;

    }

    public boolean conflict(Read read1, Read read2) {

        LocalTime t1 = LocalTime.parse(read1.getStartDate());
        LocalTime t2 = LocalTime.parse(read1.getStartDate());
        LocalTime t3 = LocalTime.parse(read2.getStartDate());
        LocalTime t4 = LocalTime.parse(read2.getStartDate());
        if (timeToMin(t1) <= timeToMin(t3) && timeToMin(t3) <= timeToMin2(t2)) {
            return true;
        }
        if (timeToMin(t1) <= timeToMin2(t4) && timeToMin2(t4) <= timeToMin2(t2)) {
            return true;
        }

        return false;
    }

    public long timeToMin(LocalTime time) {
        long miiin;
        miiin = time.getHour() * 60 + time.getMinute();
        return miiin;

    }

    public long timeToMin2(LocalTime time) {
        long miiin;
        miiin = (time.getHour() + 2) * 60 + time.getMinute();
        return miiin;

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
