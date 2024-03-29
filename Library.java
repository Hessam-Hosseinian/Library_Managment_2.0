
import java.util.ArrayList;
import java.util.Collections;
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

/**
 * Represents a library entity with information such as ID, name, foundation
 * year, desk number, and address.
 * It also contains collections of documents, borrows, and reads associated with
 * the library.
 * 
 * @author Hessam Hosseinian
 */
public class Library {

    private String libraryId;
    private String libraryName;
    private String foundationYear;
    private int deskNumber;
    private String address;
    private HashMap<String, Document> documents;
    private HashMap<String, ArrayList<Borrow>> borrows;
    private HashMap<String, Read> reads;

    /**
     * Constructs a new Library object with the specified details.
     *
     * @param libraryId      The unique identifier of the library.
     * @param libraryName    The name of the library.
     * @param foundationYear The year the library was founded.
     * @param deskNumber     The number of desks available in the library.
     * @param address        The address of the library.
     */
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

    // !------------------------------------------------------------DOCUMENT
    /**
     * Adds a book to the document collection.
     *
     * @param book The book to add.
     */
    public void addBook(Book book) {
        documents.put(book.getDocId(), book);
    }

    /**
     * Adds a thesis to the document collection.
     *
     * @param thesis The thesis to add.
     */
    public void addThesis(Thesis thesis) {
        documents.put(thesis.getDocId(), thesis);
    }

    /**
     * Adds a treasure book to the document collection.
     *
     * @param treasureBook The treasure book to add.
     */
    public void addTreasureBook(TreasureBook treasureBook) {
        documents.put(treasureBook.getDocId(), treasureBook);
    }

    /**
     * Adds a selling book to the document collection.
     *
     * @param buyableBook The selling book to add.
     */
    public void addSellingBook(BuyableBook buyableBook) {
        documents.put(buyableBook.getDocId(), buyableBook);
    }

    /**
     * Removes a resource from the document collection.
     *
     * @param docId The ID of the document to remove.
     */
    public void removeResource(String docId) {
        documents.remove(docId);
    }

    /**
     * Checks if a document with the specified ID exists in the document collection.
     *
     * @param docId The ID of the document to check.
     * @return {@code true} if the document does not exist, {@code false} otherwise.
     */
    public boolean checkDocument(String docId) {
        return documents.get(docId) == null;
    }

    // !------------------------------------------------------------BORROW
    /**
     * Attempts to borrow a document.
     *
     * @param borrow      The borrow request.
     * @param userBorrows The number of borrows by the user.
     * @param user        The user attempting to borrow the document.
     * @param document    The document being borrowed.
     * @return {@code true} if the borrow operation is successful, {@code false}
     *         otherwise.
     */
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

    /**
     * Calculates the debt incurred by a borrow.
     *
     * @param borrow     The borrow details.
     * @param returnTime The time the document is returned.
     * @param document   The document being borrowed.
     * @param user       The user borrowing the document.
     * @param check      Flag to indicate whether to update document borrow
     *                   information.
     * @return The debt amount incurred by the borrow.
     */
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

    /**
     * Calculates the debt incurred by a borrow.
     *
     * @param borrow The borrow details.
     * @param date   The date when the calculation is performed.
     * @return The debt amount incurred by the borrow.
     */
    public int checkDebt(Borrow borrow, java.util.Date date) {
        long firstMin = borrow.getDate().getTime() / 3600000;
        long secondMin = date.getTime() / 3600000;
        long periodTime = secondMin - firstMin;
        if (borrow.isStudent()) {
            if (borrow.isBook()) {
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
        if (borrow.isBook()) {
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

    /**
     * Checks if a document is already borrowed by a user.
     *
     * @param userId     The ID of the user.
     * @param resourceId The ID of the resource (document).
     * @return {@code true} if the document is already borrowed by the user,
     *         {@code false} otherwise.
     */
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

    /**
     * Counts the number of borrows by a user.
     *
     * @param userId The ID of the user.
     * @return The number of borrows by the user.
     */
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

    /**
     * Counts the number of borrows for a document.
     *
     * @param docId The ID of the document.
     * @return The number of borrows for the document.
     */
    public int countDocs(String docId) {
        ArrayList<Borrow> myBorrow = borrows.get(docId);
        if (myBorrow == null) {
            return 0;
        }
        return myBorrow.size();
    }

    /**
     * Checks if a document is currently borrowed.
     *
     * @param docId The ID of the document.
     * @return {@code true} if the document is currently borrowed, {@code false}
     *         otherwise.
     */
    public boolean checkBorrowed(String docId) {
        ArrayList<Borrow> borrows = this.borrows.get(docId);
        if (borrows == null || borrows.size() == 0) {
            return false;

        }

        return true;

    }

    // !------------------------------------------------------------RETURNING
    /**
     * Processes the return of a borrowed document.
     *
     * @param borrow   The borrow details.
     * @param document The document being returned.
     * @param user     The user returning the document.
     * @return The debt incurred by the return operation, or {@code -1} if the
     *         operation fails.
     */
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

    /**
     * Checks if there is a delay in returning documents by a user.
     *
     * @param borrow1  The borrow details.
     * @param document The document being borrowed.
     * @param user     The user borrowing the document.
     * @param userId   The ID of the user.
     * @return {@code true} if there is a delay, {@code false} otherwise.
     */
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

    // !------------------------------------------------------------BUY
    /**
     * Handles the purchase of a book.
     *
     * @param documentID The ID of the book to be purchased.
     * @return {@code true} if the book is successfully purchased, {@code false}
     *         otherwise.
     */
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

    // !------------------------------------------------------------READ
    /**
     * Handles the process of reading a treasure book.
     *
     * @param read The Read object representing the reading activity.
     * @return {@code true} if the book is successfully read, {@code false}
     *         otherwise.
     */
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

    /**
     * Checks if there is a conflict between two reading activities.
     *
     * @param read1 The first reading activity.
     * @param read2 The second reading activity.
     * @return {@code true} if there is a conflict, {@code false} otherwise.
     */
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

    // !------------------------------------------------------------SEARCH
    /**
     * Searches for documents based on the given key.
     *
     * @param key The key to search for in document titles, authors, and publishers.
     * @return A HashSet containing the IDs of documents that match the search key.
     */
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

    // !------------------------------------------------------------CATEGORY_REPORT
    /**
     * Generates a report of the available copies of documents belonging to the
     * specified category and its subcategories.
     *
     * @param category The category for which the report is generated.
     * @return An array containing counts of available copies of different document
     *         types:
     *         Index 0: Count of available copies of books
     *         Index 1: Count of available copies of theses
     *         Index 2: Count of available copies of treasure books
     *         Index 3: Count of available copies of buyable books
     */
    public int[] categoryReport(Category category) {
        int[] bookCounts = new int[4];
        countBooksRecursivelyHelper(category, bookCounts);
        return bookCounts;
    }

    /**
     * Helper method to recursively count available copies of documents belonging to
     * the specified category and its subcategories.
     *
     * @param category   The category for which document counts are being
     *                   calculated.
     * @param bookCounts An array to store the counts of available copies of
     *                   different document types.
     */
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

    // !------------------------------------------------------------LIBRARY_REPORT
    /**
     * Generates a report summarizing the status of documents in the library.
     *
     * @return A string containing the counts of available copies, borrowed copies,
     *         and specific types of documents in the library:
     *         - Count of available copies of books
     *         - Count of available copies of theses
     *         - Count of borrowed copies of books
     *         - Count of borrowed copies of theses
     *         - Count of available copies of treasure books
     *         - Count of available copies of buyable books
     */
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

    // !------------------------------------------------------------PASSED_DEAD_LINE
    /**
     * Generates a report of documents that have passed their deadline for return
     * based on the given date.
     *
     * @param date The date to check for overdue documents.
     * @return A StringBuilder containing the IDs of documents that have passed
     *         their deadline, separated by "|" character.
     *         If no documents are found to be overdue, an empty StringBuilder is
     *         returned.
     */
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

    // !------------------------------------------------------------REPORT_MOST_POPULAR
    /**
     * Generates a report of the most popular documents, based on the total time
     * they have been borrowed.
     * The report includes the document IDs, the count of times each document has
     * been borrowed, and the total days borrowed.
     * If there are no documents of a certain type (book or thesis), "null" is
     * reported for that type.
     *
     * @return A formatted string containing the most popular book and thesis based
     *         on borrowing statistics.
     */
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

    // !------------------------------------------------------------REPORT_SELL
    /**
     * Generates a report of the selling statistics, including the total number of
     * books sold, the total money earned from sales,
     * and details of the best-selling book (document ID, number sold, and total
     * revenue).
     *
     * @return A formatted string containing the selling statistics and details of
     *         the best-selling book.
     */
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
    // !------------------------------------------------------------SETERS_AND_GETERS

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
