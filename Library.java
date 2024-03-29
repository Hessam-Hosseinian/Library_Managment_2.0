
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

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
        if (check) {
            document.setDaysOfBorrowed((int) Math.ceil(periodTime / 24.0));
            document.setTimeThatdocIsUnderBorrow(periodTime);
        }
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
                document.increaseCountOfBorrowed();
                return true;
            }
            return false;
        } else if (user instanceof Staff || user instanceof Professor) {
            if (userBorrows < 5) {
                borrows1.add(borrow);
                borrows.put(borrow.getDocumentId(), borrows1);
                document.decreaseAvailableCopyNumber();
                document.increaseCountOfBorrowed();
                return true;
            }
            return false;
        }
        return false;
    }

    private boolean isBorrowedByUser(String userId, String resourceId) {
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

    public int[] categoryReport(Category category) {
        int[] bookCounts = new int[4];
        countBooksRecursivelyHelper(category, bookCounts);
        return bookCounts;
    }

    public void countBooksRecursivelyHelper(Category category, int[] bookCounts) {

        for (Document document : documents.values()) {
            if (document.getCategoryId().equals(category.getCategoryId())) {

                if (document instanceof Book) {
                    bookCounts[0] += document.getAvailableCopyNumber();
                }
                if (document instanceof Thesis) {
                    bookCounts[1] += document.getAvailableCopyNumber();
                }
                if (document instanceof TreasureBook) {
                    bookCounts[2] += document.getAvailableCopyNumber();
                }
                if (document instanceof BuyableBook) {
                    bookCounts[3] += document.getAvailableCopyNumber();
                }
            }
        }
        if (category.getCategoryId().equals("null")) {
            return;
        }
        ArrayList<Category> subs = category.getSubCategory();
        for (Category category2 : subs) {
            countBooksRecursivelyHelper(category2, bookCounts);
        }

    }

    public String libraryReport() {
        int bookNum = 0, thesisNum = 0, ganjineNum = 0, sellingBookNum = 0, borrowedBook = 0, borrowedThesis = 0;

        for (Document document : documents.values()) {

            if (document instanceof Book) {
                bookNum += document.getAvailableCopyNumber();
                borrowedBook += (document.getCopyNumber() - document.getAvailableCopyNumber());
            }
            if (document instanceof Thesis) {
                thesisNum += document.getAvailableCopyNumber();
                borrowedThesis += (document.getCopyNumber() - document.getAvailableCopyNumber());

            }
            if (document instanceof TreasureBook) {
                ganjineNum += document.getAvailableCopyNumber();
            }
            if (document instanceof BuyableBook) {
                sellingBookNum += document.getAvailableCopyNumber();
            }
        }

        return "" + bookNum + " " + thesisNum + " " + borrowedBook + " " + borrowedThesis + " " + ganjineNum + " "
                + sellingBookNum;
    }

    public int checkDebt(Borrow borrow, java.util.Date date) {
        long firstMin = borrow.getDate().getTime() / 3600000; // getTime return time as millisecond
        long secondMin = date.getTime() / 3600000; // getTime return time as millisecond
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

    public StringBuilder reportPassedDeadline(Date date) {
        HashSet<String> outPut = new HashSet<>();
        StringBuilder hold = new StringBuilder();
        for (ArrayList<Borrow> borrows1 : new ArrayList<>(borrows.values())) {
            for (Borrow borrow : borrows1) {
                if (checkDebt(borrow, date) != 0) {
                    outPut.add(borrow.getDocumentId());
                }
            }
        }

        ArrayList<String> outputArray = new ArrayList<>(outPut);
        Collections.sort(outputArray);
        for (String i : outputArray) {
            hold.append(i);
            hold.append("|");
        }
        return hold;
    }

    public String reportMostPopular() {
        long tmp1 = 0;
        long tmp2 = 0;
        Document documentBook = null;
        Document documentThesis = null;

        for (Document document : documents.values()) {
            if (document instanceof Book) {

                if (document.getTimeThatdocIsUnderBorrow() >= tmp1) {

                    tmp1 = document.getTimeThatdocIsUnderBorrow();
                    documentBook = document;

                }

            }
            if (document instanceof Thesis) {

                if (document.getTimeThatdocIsUnderBorrow() >= tmp2) {
                    tmp2 = document.getTimeThatdocIsUnderBorrow();
                    documentThesis = document;

                }

            }

        }
        if (documentBook == null) {
            return "null" + "\n"
                    + documentThesis.getDocId() + " " + documentThesis.getCountOfBorroewed() + " "
                    + documentThesis.getDaysOfBorrowed();
        }
        if (documentThesis == null) {
            return documentBook.getDocId() + " " + documentBook.getCountOfBorroewed() + " "
                    + documentBook.getDaysOfBorrowed() + "\n"
                    + "null";
        }

        return "" + documentBook.getDocId() + " " +
                documentBook.getCountOfBorroewed() + " "
                + documentBook.getDaysOfBorrowed() + "\n" +
                documentThesis.getDocId() + " " + documentThesis.getCountOfBorroewed() + " "
                + documentThesis.getDaysOfBorrowed();

    }

    public String reportSell() {

        int x = 0;
        int soldBooks = 0;
        int moneyErnt = 0;

        Document maxSell = null;
        for (Document document : documents.values()) {

            int thisBook = 0;
            if (document instanceof BuyableBook) {
                thisBook = (document.getCopyNumber() - document.getAvailableCopyNumber());
                soldBooks += thisBook;
                moneyErnt += ((BuyableBook) document).calculatePrice() * thisBook;

                if (document.getCopyNumber() - document.getAvailableCopyNumber() > x) {
                    x = document.getCopyNumber() - document.getAvailableCopyNumber();
                    maxSell = document;

                }

            }

        }

        return "" + soldBooks + " " + moneyErnt + "\n" + maxSell.getDocId() + " "
                + (maxSell.getCopyNumber() - maxSell.getAvailableCopyNumber()) + " "
                + ((BuyableBook) maxSell).calculatePrice()
                        * (maxSell.getCopyNumber() - maxSell.getAvailableCopyNumber());

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
