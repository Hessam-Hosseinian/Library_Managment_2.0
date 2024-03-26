
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import user.Professor;
import user.Staff;
import user.Student;
import user.User;
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

    private int checkDebt(Borrow borrow, Date returnTime, Document document, User user, boolean check) {
        long firstMin = borrow.getDate().getTime() / 3600000;
        long secondMin = returnTime.getTime() / 3600000;
        long periodTime = secondMin - firstMin;
        // if (check) {
        // document.setDayOfBorrowed((int) Math.ceil(periodTime / 24.0));
        // }
        if (user instanceof Student) {
            if (document instanceof Book) {
                if (periodTime < (10 * 24)) {
                    return 0;
                }
                return (int) ((periodTime - (10 * 24)) * 50);
            }
            if (periodTime < (7 * 24)) {
                return 0;
            }
            return (int) ((periodTime - (7 * 24)) * 50);
        }
        if (document instanceof Book) {
            if (periodTime < (14 * 24)) {
                return 0;
            }
            return (int) ((periodTime - (14 * 24)) * 100);
        }
        if (periodTime < (10 * 24)) {
            return 0;
        }
        return (int) ((periodTime - (10 * 24)) * 100);
    }

    public boolean checkDocument(String docId) {

        if (documents.get(docId) == null) {
            return true;

        }
        return false;

    }

    public boolean borrow(Borrow borrow, int userBorrows, User user, Document document) {
        ArrayList<Borrow> borrows1 = borrows.get(borrow.getDocumentId());
        if (borrows1 == null) {
            borrows1 = new ArrayList<>();
        }
        if (document instanceof TreasureBook || document instanceof BuyableBook) {
            return false;
        }
        if (countDocs(borrow.getDocumentId()) >= document.getCopyNumber()) {
            return false;
        }
        if (isBorrowedByUser(borrow.getUserId(), borrow.getDocumentId())) {
            return false;
        }
        if (user instanceof Student) {
            if (userBorrows < 3) {
                borrows1.add(borrow);
                borrows.put(borrow.getDocumentId(), borrows1);
                document.decreaseAvailableCopyNumber();
                // todo document.borrowed();
                return true;
            }
            return false;
        } else if (user instanceof Staff || user instanceof Professor) {
            if (userBorrows < 5) {
                borrows1.add(borrow);
                borrows.put(borrow.getDocumentId(), borrows1);
                document.decreaseAvailableCopyNumber();
                // todo resource.borrowed();
                return true;
            }
            return false;
        }
        return false;
    }

    private boolean isBorrowedByUser(String userId, String resourceId) { // check that user get this resource or not
        ArrayList<Borrow> myBorrow = borrows.get(resourceId);
        if (myBorrow == null) {
            return false;
        }
        for (Borrow borrow : myBorrow) {
            if (borrow.getUserId().equals(userId)) {
                return true;
            }
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

    public void removeResource(String docId) {

        documents.remove(docId);

    }

    public boolean checkBorrowed(String docId) {
        ArrayList<Borrow> borrows = this.borrows.get(docId);
        if (borrows == null || borrows.size() == 0) {
            return false;

        }

        return true;

    }

    public int returning(Borrow borrow, Document document, User user) {
        ArrayList<Borrow> borrows = this.borrows.get(borrow.getDocumentId());
        Borrow itsBorrow = null;
        if (borrows == null) {
            return -1;
        }
        for (Borrow hold : borrows) {
            if (hold.getUserId().equals(borrow.getUserId())) {
                itsBorrow = hold;
            }
        }
        if (itsBorrow == null) {
            return -1;
        }
        int debt = checkDebt(itsBorrow, borrow.getDate(), document, user, true);
        user.setDebt(debt);
        borrows.remove(itsBorrow);
        document.increaseAvailableCopyNumber();
        return debt;
    }

    public boolean hasDelay(Borrow borrow1, Document document, User user, String userId) {
        int x = 0;
        for (ArrayList<Borrow> myBorrow : borrows.values()) {
            if (myBorrow == null) {
                return false;
            }
            for (Borrow borrow : myBorrow) {
                if (borrow.getUserId().equals(userId)) {
                    x += checkDebt(borrow, borrow1.getDate(), document, user, false);
                }
            }
        }
        return x > 0;
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

        if (document.getAvailableCopyNumber() == 0) {

            System.out.println("not-allowed");
            return false;
        }
        document.decreaseAvailableCopyNumber();
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

        for (Read read2 : reads.values()) {

            if (read2.getBookId().equals(read.getBookId())
                    && !conflict(read, read2)) {

                System.out.println("not-allowed");
                return false;
            }

        }

        reads.put(read.getBookId(), read);

        return true;

    }

    public boolean conflict(Read read1, Read read2) {
        Date dateHold = read1.getDate1();
        Date dateHold2 = read2.getDate1();

        if (dateHold == null || dateHold2 == null) {

            return true;
        }
        long firstTime = dateHold.getTime() / 3600000;
        long secondTime = dateHold2.getTime() / 3600000;
        long periodTime = secondTime - firstTime;
        return Math.abs(periodTime) >= 2;
    }

    public HashSet<String> search(String key) {
        HashSet<String> output = new HashSet<>();
        for (Document document : documents.values()) {
            if (document.getTitle().toLowerCase().contains(key.toLowerCase())) {
                output.add(document.getDocId());
            }
            if (document.getAuthor().toLowerCase().contains(key.toLowerCase())) {
                output.add(document.getDocId());
            }
            if (document.getPublisher().toLowerCase().contains(key.toLowerCase())) {
                output.add(document.getDocId());
            }

            if (document instanceof Thesis) {

                Thesis thesis = (Thesis) document;
                if (thesis.getStudentName().toLowerCase().contains(key.toLowerCase())) {
                    output.add(thesis.getDocId());
                }
                if (thesis.getProfessorName().toLowerCase().contains(key.toLowerCase())) {
                    output.add(thesis.getDocId());
                }

            }

        }
        return output;
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
