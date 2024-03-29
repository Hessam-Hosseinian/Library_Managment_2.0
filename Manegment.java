import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import doc.Book;
import doc.BuyableBook;
import doc.Document;
import doc.Thesis;
import doc.TreasureBook;
import user.Admin;
import user.Manager;
import user.Professor;
import user.Staff;
import user.Student;
import user.User;

/**
 * Initializes a new instance of the Management class.
 * This class manages libraries, users, and categories within the system.
 * Upon initialization, it sets up an admin user and a null category.
 * 
 * @author Hessam Hosseinian
 */
public class Manegment {

    private HashMap<String, Library> libraries;

    private HashMap<String, User> users;

    private static HashMap<String, Category> categories;

    public Manegment() {

        this.libraries = new HashMap<>();
        this.users = new HashMap<>();
        this.categories = new HashMap<>();

        Admin admin = new Admin("admin", "AdminPass", "-", "-", "-", "-", "-");
        users.put("admin", admin);

        Category nullCategory = new Category("null", "null");
        categories.put("null", nullCategory);

    }

    // !-------------------------------------------------------------------------------------------CHECK_ADMIN_PERMISSION
    /**
     * Checks if a user with admin permission has access.
     *
     * @param id       The ID of the user.
     * @param password The password of the user.
     * @return true if the user with admin permission is authenticated successfully,
     *         false otherwise.
     */
    public boolean checkAdminPermission(String id, String passwoed) {

        User targetUser = users.get(id);

        if (targetUser != null && targetUser.getPassword().equals(passwoed) && targetUser instanceof Admin) {

            return true;
        }
        if (targetUser == null) {
            System.out.println("not-found");
        }

        else if (targetUser != null && !targetUser.getPassword().equals(passwoed))

        {
            System.out.println("invalid-pass");

        }

        else if (targetUser != null && targetUser.getPassword().equals(passwoed) && !(targetUser instanceof Admin)) {

            System.out.println("permission-denied");

        }

        return false;

    }

    // !-------------------------------------------------------------------------------------------CHECK_MANAGER_PERMISSION
    /**
     * Checks if a user with manager permission has access to a specific library.
     *
     * @param id        The ID of the user.
     * @param password  The password of the user.
     * @param libraryId The ID of the library to be checked.
     * @return true if the user with manager permission has access to the specified
     *         library, false otherwise.
     */
    public boolean checkManagerPermission(String id, String passwoed, String libraryId) {

        Library targetLibrary = libraries.get(libraryId);
        if (targetLibrary == null) {

            System.out.println("not-found");
            return false;

        }

        User targetUser = users.get(id);

        if (targetUser instanceof Manager) {
            Manager targetManager = (Manager) targetUser;

            if (targetUser != null && targetUser.getPassword().equals(passwoed) && targetUser instanceof Manager
                    && targetLibrary.getLibraryId().equals(targetManager.getLibraryId())) {

                return true;
            }

            else if (targetUser != null && targetUser.getPassword().equals(passwoed) && targetUser instanceof Manager
                    && !(targetLibrary.getLibraryId().equals(targetManager.getLibraryId()))) {

                System.out.println("permission-denied");

            }
        }
        if (targetUser == null) {

            System.out.println("not-found");
        }

        else if (targetUser != null && !targetUser.getPassword().equals(passwoed)) {
            System.out.println("invalid-pass");

        }

        else if (targetUser != null && targetUser.getPassword().equals(passwoed)
                && !(targetUser instanceof Manager)) {

            System.out.println("permission-denied");

        }

        return false;

    }

    // !-------------------------------------------------------------------------------------------ADD_LIBRARY
    /**
     * Adds a library to the system.
     *
     * @param library The Library object representing the library to be added.
     * @return A string indicating the result of the operation. Possible values are:
     *         - "duplicate-id" if a library with the same ID already exists in the
     *         system.
     *         - "success" if the library is added successfully.
     */
    public String addLibrary(Library library) {

        if (libraries.get(library.getLibraryId()) != null) {
            return "duplicate-id";

        }

        libraries.put(library.getLibraryId(), library);
        return "success";

    }

    // !-------------------------------------------------------------------------------------------ADD_CATEGORY
    /**
     * Adds a category to the system.
     *
     * @param category    The Category object representing the category to be added.
     * @param subCategory The ID of the existing sub-category to which the new
     *                    category belongs.
     * @return A string indicating the result of the operation. Possible values are:
     *         - "duplicate-id" if a category with the same ID already exists in the
     *         system.
     *         - "not-found" if the specified sub-category does not exist in the
     *         system.
     *         - "success" if the category is added successfully.
     */
    public String addCategory(Category category, String subCategory) {
        if (categories.get(category.getCategoryId()) != null) {
            return "duplicate-id";

        }
        Category subbCategory = categories.get(subCategory);
        if (subbCategory == null) {
            return "not-found";

        }
        subbCategory.setSubs(category);
        categories.put(category.getCategoryId(), category);
        return "success";

    }

    // !-------------------------------------------------------------------------------------------ADD_STUDENT
    /**
     * Adds a student to the system.
     *
     * @param student The Student object representing the student to be added.
     * @return A string indicating the result of the operation. Possible values are:
     *         - "duplicate-id" if a user with the same ID already exists in the
     *         system.
     *         - "success" if the student is added successfully.
     */
    public String addStudent(Student student) {

        if (users.get(student.getUserId()) != null) {
            return "duplicate-id";

        }
        users.put(student.getUserId(), student);
        return "success";

    }

    // !-------------------------------------------------------------------------------------------ADD_STAFF
    /**
     * Adds a staff member to the system.
     *
     * @param staff The Staff object representing the staff member to be added.
     * @return A string indicating the result of the operation. Possible values are:
     *         - "duplicate-id" if a user with the same ID already exists in the
     *         system.
     *         - "success" if the staff member is added successfully.
     */
    public String addStaff(Staff staff) {

        if (users.get(staff.getUserId()) != null) {
            return "duplicate-id";

        }
        users.put(staff.getUserId(), staff);
        return "success";

    }

    /**
     * Adds a professor to the system.
     *
     * @param professor The Professor object representing the professor to be added.
     * @return A string indicating the result of the operation. Possible values are:
     *         - "duplicate-id" if a user with the same ID already exists in the
     *         system.
     *         - "success" if the professor is added successfully.
     */
    public String addStaff(Professor professor) {
        if (users.get(professor.getUserId()) != null) {
            return "duplicate-id";

        }
        users.put(professor.getUserId(), professor);
        return "success";

    }

    // !-------------------------------------------------------------------------------------------ADD_MANAGER
    /**
     * Adds a manager to the system.
     *
     * @param manager The Manager object representing the manager to be added.
     * @return A string indicating the result of the operation. Possible values are:
     *         - "duplicate-id" if a user with the same ID already exists in the
     *         system.
     *         - "not-found" if the library specified by the manager is not found.
     *         - "success" if the manager is added successfully.
     */
    public String addManager(Manager manager) {
        if (users.get(manager.getUserId()) != null) {
            return "duplicate-id";

        }

        Library targetLibrary = libraries.get(manager.getLibraryId());

        if (targetLibrary == null) {

            return "not-found";

        }
        // targetLibrary.addManager(manager);
        users.put(manager.getUserId(), manager);
        return "success";

    }

    // !-------------------------------------------------------------------------------------------REMOVE_USER
    /**
     * Removes a user from the system.
     *
     * @param userId The ID of the user to be removed.
     * @return A string indicating the result of the operation. Possible values are:
     *         - "not-found" if the user with the specified ID is not found in the
     *         system.
     *         - "not-allowed" if the user has an outstanding debt or active
     *         borrowings in any library.
     *         - "success" if the user is successfully removed from the system.
     */
    public String removeUser(String userId) {
        User targeUser = users.get(userId);
        if (targeUser == null) {
            return "not-found";

        }
        if (targeUser.getDebt() != 0) {
            return "not-allowed";

        }

        for (Library library : libraries.values()) {

            if (library.countBorrows(userId) != 0) {
                return "not-allowed";
            }
        }

        users.remove(userId);
        return "success";

    }

    // !-------------------------------------------------------------------------------------------ADD_BOOK
    /**
     * Adds a book to the library's inventory.
     *
     * @param book The Book object representing the book to be added.
     * @return A string indicating the result of the operation. Possible values are:
     *         - "not-found" if the library or the category specified by the book is
     *         not found.
     *         - "duplicate-id" if a document with the same ID already exists in the
     *         library's inventory.
     *         - "success" if the book is added successfully.
     */
    public String addBook(Book book) {
        Library library = libraries.get(book.getLibraryId());
        if (library == null) {

            return "not-found";
        }
        Category category = categories.get(book.getCategoryId());
        if (category == null) {

            return "not-found";
        }
        if (library.getDocuments(book.getDocId()) != null) {
            return "duplicate-id";
        }
        library.addBook(book);
        return "success";

    }

    // !-------------------------------------------------------------------------------------------ADD_THESIS
    /**
     * Adds a thesis document to the library's inventory.
     *
     * @param thesis The Thesis object representing the thesis document to be added.
     * @return A string indicating the result of the operation. Possible values are:
     *         - "not-found" if the library or the category specified by the thesis
     *         is not found.
     *         - "duplicate-id" if a document with the same ID already exists in the
     *         library's inventory.
     *         - "success" if the thesis document is added successfully.
     */
    public String addThesis(Thesis thesis) {
        Library library = libraries.get(thesis.getLibraryId());
        if (library == null) {
            return "not-found";
        }
        Category category = categories.get(thesis.getCategoryId());
        if (category == null) {
            return "not-found";
        }
        if (library.getDocuments(thesis.getDocId()) != null) {
            return "duplicate-id";
        }
        library.addThesis(thesis);
        return "success";
    }

    // !-------------------------------------------------------------------------------------------ADD_TREASURE_BOOK
    /**
     * Adds a treasure book to the library's inventory.
     *
     * @param treasureBook The TreasureBook object representing the book to be
     *                     added.
     * @return A string indicating the result of the operation. Possible values are:
     *         - "not-found" if the library or the category specified by the book is
     *         not found.
     *         - "duplicate-id" if a document with the same ID already exists in the
     *         library's inventory.
     *         - "success" if the book is added successfully.
     */

    public String addTreasureBook(TreasureBook treasureBook) {
        Library library = libraries.get(treasureBook.getLibraryId());
        if (library == null) {
            return "not-found";
        }
        Category category = categories.get(treasureBook.getCategoryId());
        if (category == null) {
            return "not-found";
        }
        if (library.getDocuments(treasureBook.getDocId()) != null) {
            return "duplicate-id";
        }
        library.addTreasureBook(treasureBook);
        return "success";
    }

    // !-------------------------------------------------------------------------------------------ADD_SELLING_BOOK
    /**
     * Adds a buyable book to the library's inventory.
     *
     * @param buyableBook The BuyableBook object representing the book to be added.
     * @return A string indicating the result of the operation. Possible values are:
     *         - "not-found" if the library or the category specified by the book is
     *         not found.
     *         - "duplicate-id" if a document with the same ID already exists in the
     *         library's inventory.
     *         - "success" if the book is added successfully.
     */

    public String addSellingBook(BuyableBook buyableBook) {
        Library library = libraries.get(buyableBook.getLibraryId());
        if (library == null) {
            return "not-found";
        }
        Category category = categories.get(buyableBook.getCategoryId());
        if (category == null) {
            return "not-found";
        }
        if (library.getDocuments(buyableBook.getDocId()) != null) {
            return "duplicate-id";
        }
        library.addSellingBook(buyableBook);
        return "success";
    }

    // !-------------------------------------------------------------------------------------------REMOVE_RESOURCE
    /**
     * Removes a resource (document) from the specified library.
     *
     * @param docId     The ID of the document to be removed.
     * @param libraryId The ID of the library from which the document is to be
     *                  removed.
     * @return A string indicating the result of the operation. Possible values are:
     *         - "not-found" if the library or the document is not found.
     *         - "not-allowed" if the document cannot be removed because it is
     *         currently borrowed.
     *         - "success" if the document is removed successfully.
     */

    public String removeResource(String docId, String libraryId) {

        Library targetLibrary = libraries.get(libraryId);
        if (targetLibrary == null) {
            return "not-found";

        }
        if (targetLibrary.getDocuments(docId) == null) {
            return "not-found";

        }

        if (targetLibrary.checkBorrowed(docId)) {

            return "not-allowed";
        }

        targetLibrary.removeResource(docId);

        return "success";
    }

    // !-------------------------------------------------------------------------------------------BORROW
    /**
     * Handles the borrowing of a document by a user.
     *
     * @param borrow   The Borrow object containing information about the borrowing
     *                 activity.
     * @param password The password of the user borrowing the document.
     * @return A string indicating the result of the operation. Possible values are:
     *         - "not-found" if the user, library, or document is not found.
     *         - "invalid-pass" if the password provided is incorrect.
     *         - "not-allowed" if the user is not allowed to borrow the document due
     *         to existing debt or delay in returning previous documents.
     *         - "success" if the document is borrowed successfully.
     */
    public String borrow(Borrow borrow, String password) {

        User user = users.get(borrow.getUserId());
        if (user == null) {
            return "not-found";

        }
        if (!user.getPassword().equals(password)) {
            return "invalid-pass";

        }
        Library library = libraries.get(borrow.getLibraryId());
        if (library == null) {
            return "not-found";

        }
        Document document = library.getDocuments(borrow.getDocumentId());
        if (document == null) {
            return "not-found";

        }
        if (user.getDebt() != 0) {
            return "not-allowed";
        }
        if (checkDelay(borrow, document, user)) {

            return "not-allowed";
        }
        if (!library.borrow(borrow, countBorrow(borrow.getUserId()), user, document)) {

            return "not-allowed";
        }
        return "success";

    }

    /**
     * Counts the number of documents borrowed by the specified user across all
     * libraries.
     *
     * @param userId The ID of the user for whom the count is calculated.
     * @return The total number of documents borrowed by the user.
     */
    public int countBorrow(String userId) {
        int borrowed = 0;
        for (Library library : libraries.values()) {
            borrowed += library.countBorrows(userId);
        }
        return borrowed;
    }

    /**
     * Checks if the specified borrow is delayed for the given document and user
     * across all libraries.
     *
     * @param borrow   The Borrow object containing information about the borrowing
     *                 activity.
     * @param document The Document object representing the document being borrowed.
     * @param user     The User object representing the user borrowing the document.
     * @return True if there is a delay in returning the document, otherwise false.
     */
    private boolean checkDelay(Borrow borrow, Document document, User user) {
        for (Library library : libraries.values()) {
            if (library.hasDelay(borrow, document, user, borrow.getUserId())) {
                return true;
            }
        }
        return false;
    }

    // !-------------------------------------------------------------------------------------------RETURNING
    /**
     * Handles the returning of a borrowed document by a user.
     *
     * @param borrow   The Borrow object containing information about the borrowing
     *                 activity.
     * @param password The password of the user returning the document.
     * @return A string indicating the result of the operation. Possible values are:
     *         - "not-found" if the user, library, or document is not found, or if
     *         there is an error during the return process.
     *         - "invalid-pass" if the password provided is incorrect.
     *         - A positive integer representing the debt owed by the user if there
     *         is any.
     *         - "success" if the document is returned successfully and there is no
     *         debt owed by the user.
     */
    public String returning(Borrow borrow, String password) {

        User user = users.get(borrow.getUserId());
        if (user == null) {
            return "not-found";

        }
        if (!user.getPassword().equals(password)) {
            return "invalid-pass";

        }
        Library library = libraries.get(borrow.getLibraryId());
        if (library == null) {
            return "not-found";

        }
        Document document = library.getDocuments(borrow.getDocumentId());
        if (document == null) {
            return "not-found";

        }
        int debt = library.returning(borrow, document, user);
        if (debt < 0) {
            return "not-found";
        }
        if (debt > 0) {
            return "" + debt;
        }
        return "success";
    }

    // !-------------------------------------------------------------------------------------------BUY
    /**
     * Handles the purchase of a document by a user.
     *
     * @param userId     The ID of the user making the purchase.
     * @param pass       The password of the user making the purchase.
     * @param libraryId  The ID of the library from which the document is purchased.
     * @param documentId The ID of the document being purchased.
     */
    public void buy(String userId, String pass, String libraryId, String documentId) {

        User targetUser = users.get(userId);
        if (targetUser == null) {

            System.out.println("not-found");
            return;
        }
        if (!targetUser.getPassword().equals(pass)) {

            System.out.println("invalid-pass");
            return;
        }
        if (targetUser instanceof Manager) {
            System.out.println("permission-denied");
            return;

        }
        if (targetUser.getDebt() != 0) {
            System.out.println("not-allowed");
            return;
        }
        Library targetLibrary = libraries.get(libraryId);
        if (targetLibrary == null) {
            System.out.println("not-found");
            return;
        }
        if (targetLibrary.checkDocument(documentId)) {
            System.out.println("not-found");
            return;

        }

        if (targetLibrary.buyBook(documentId)) {
            System.out.println("success");
            return;
        }
        return;

    }

    // !-------------------------------------------------------------------------------------------READ
    /**
     * Records a reading activity performed by a user.
     *
     * @param read     The Read object containing information about the reading
     *                 activity.
     * @param password The password of the user performing the reading activity.
     */
    public void read(Read read, String passwoed) {

        User targetUser = users.get(read.getUserId());
        if (targetUser == null) {
            System.out.println("not-found");
            return;
        }
        if (!targetUser.getPassword().equals(passwoed)) {
            System.out.println("invalid-pass");
            return;
        }
        if (!(targetUser instanceof Professor)) {

            System.out.println("permission-denied");
            return;

        }
        if (targetUser.getDebt() != 0) {

            System.out.println("not-allowed");
            return;
        }
        Library targetLibrary = libraries.get(read.getLibraryId());
        if (targetLibrary == null) {

            System.out.println("not-found");
            return;
        }

        if (targetLibrary.checkDocument(read.getBookId())) {

            System.out.println("not-found");
            return;

        }
        if (targetLibrary.readBook(read)) {

            System.out.println("success");
            return;
        }
        return;

    }

    // !-------------------------------------------------------------------------------------------ADD_COMMENT
    /**
     * Adds a comment to a document by the specified user.
     *
     * @param userId     The ID of the user adding the comment.
     * @param pass       The password of the user adding the comment.
     * @param libraryId  The ID of the library where the document is located.
     * @param documentId The ID of the document to which the comment is added.
     * @param strComment The comment to be added.
     * @return A string indicating the result of the operation. Possible values are:
     *         - "not-found" if the user, library, or document is not found.
     *         - "invalid-pass" if the password provided is incorrect.
     *         - "permission-denied" if the user does not have permission to add
     *         comments.
     *         - "success" if the comment is added successfully.
     */
    public String addComment(String userId, String pass, String libraryId, String documentId, String strComment) {

        User targetUser = users.get(userId);
        if (targetUser == null) {
            return "not-found";
        }
        if (!targetUser.getPassword().equals(pass)) {
            return "invalid-pass";

        }
        if (targetUser instanceof Staff || targetUser instanceof Manager) {
            return "permission-denied";

        }
        Library targetLibrary = libraries.get(libraryId);
        if (targetLibrary == null) {

            return "not-found";

        }
        Document targetDocument = targetLibrary.getDocuments(documentId);
        if (targetDocument == null) {

            return "not-found";

        }
        targetDocument.addComment(strComment);
        return "success";

    }

    // !-------------------------------------------------------------------------------------------SEARCH
    /**
     * Searches for documents across all libraries based on the provided key.
     *
     * @param key The search key used to find documents.
     * @return A StringBuilder object containing the search results. If no documents
     *         match the search criteria, returns "not-found". Otherwise, returns a
     *         list of document IDs separated by '|' character.
     */
    public StringBuilder search(String key) {
        ArrayList<String> output = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (Library library : libraries.values()) {
            output.addAll(library.search(key));
        }
        ArrayList<String> hold = new ArrayList<>(output);
        Collections.sort(hold);
        for (String str : hold) {
            stringBuilder.append(str);
            stringBuilder.append("|");
        }
        if (stringBuilder.length() == 0) {
            return stringBuilder.append("not-found");
        }
        stringBuilder = stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder;
    }

    // !-------------------------------------------------------------------------------------------SEARCH_USER
    /**
     * Searches for users based on the provided criteria.
     *
     * @param userId The ID of the user performing the search.
     * @param pass   The password of the user performing the search.
     * @param key    The search key used to find users by first name or last name.
     * @return A StringBuilder object containing the search results. If the user is
     *         not found, returns "not-found". If the password is invalid, returns
     *         "invalid-pass". If the user does not have permission to perform the
     *         search, returns "permission-denied". If no users match the search
     *         criteria, returns "not-found". Otherwise, returns a list of user IDs
     *         separated by '|' character.
     */
    public StringBuilder searchUser(String userId, String pass, String key) {
        User user = users.get(userId);

        if (user == null) {
            return new StringBuilder("not-found");

        }
        if (!user.getPassword().equals(pass)) {
            return new StringBuilder("invalid-pass");

        }
        if (user instanceof Student) {

            return new StringBuilder("permission-denied");

        }

        HashSet<String> output = new HashSet<>();
        StringBuilder searchID = new StringBuilder();

        for (User user2 : users.values()) {
            if (!(user2 instanceof Admin)) {

                if (user2.getFirstName().toLowerCase().contains(key.toLowerCase())) {
                    output.add(user2.getUserId());
                }
                if (user2.getLastName().toLowerCase().contains(key.toLowerCase())) {
                    output.add(user2.getUserId());
                }
            }
        }

        ArrayList<String> outputArray = new ArrayList<>(output);
        Collections.sort(outputArray);
        for (String i : outputArray) {
            searchID.append(i);
            searchID.append("|");
        }
        if (searchID.length() == 0) {
            return new StringBuilder("not-found");
        }
        searchID = searchID.deleteCharAt(searchID.length() - 1);
        return searchID;
    }

    // !-------------------------------------------------------------------------------------------CATEGORY_REPORT
    /**
     * Generates a report for the specified category in the specified library.
     *
     * @param categoryId The ID of the category for which the report is generated.
     * @param libraryId  The ID of the library for which the report is generated.
     */
    public void categoryReport(String categoryId, String librayId) {

        Library targetLibrary = libraries.get(librayId);
        if (targetLibrary == null) {
            System.out.println("not-found");
            return;

        }
        Category targetCategory = categories.get(categoryId);
        if (targetCategory == null) {
            System.out.println("not-found");
            return;

        }

        int[] count = targetLibrary.categoryReport(targetCategory);
        System.out.println(count[0] + " " + count[1] + " " + count[2] + " " + count[3]);

    }

    /**
     * Retrieves the category with the specified ID from the collection of
     * categories.
     *
     * @param categoryId The ID of the category to retrieve.
     * @return The Category object corresponding to the specified ID, or null if no
     *         category with that ID exists.
     */
    public static Category getCategory(String categoryId) {

        return categories.get(categoryId);
    }

    // !-------------------------------------------------------------------------------------------LIBRARY_REPORT
    /**
     * Generates a report for the specified library.
     *
     * @param libraryId The ID of the library for which the report is generated.
     * @return A string representing the report of the specified library. If the
     *         library with the specified ID is not found, returns "not-found".
     */
    public String libraryReport(String libraryId) {

        Library targetLibrary = libraries.get(libraryId);
        if (targetLibrary == null) {

            return "not-found";

        }
        return targetLibrary.libraryReport();

    }

    // !-------------------------------------------------------------------------------------------REPORT_PASSED_DEAD_LINE
    /**
     * Generates a report of documents that have passed their deadline for return in
     * the specified library.
     *
     * @param libraryId The ID of the library for which the report is generated.
     * @param date      The date used to determine if documents have passed their
     *                  deadline.
     * @return A StringBuilder object containing the report of documents that have
     *         passed their deadline. If the library with the specified ID is not
     *         found, returns "not-found". If there are no documents that have
     *         passed their deadline, returns "none".
     */
    public StringBuilder reportPasseDeadline(String libraryId, Date date) {
        StringBuilder output;
        Library library = libraries.get(libraryId);
        if (library == null) {
            return new StringBuilder("not-found");
        }
        output = library.reportPassedDeadline(date);
        if (output.length() == 0) {
            return new StringBuilder("none");
        }
        output = output.deleteCharAt(output.length() - 1);
        return output;
    }

    // !-------------------------------------------------------------------------------------------REPORT_PENALTIES_SUM
    /**
     * Generates a report of the sum of penalties for all users in the system.
     *
     * @return A string representing the sum of penalties. The sum is calculated by
     *         iterating through all users and adding up their respective penalties.
     */
    public String reportPenaltiesSum() {

        int sum = 0;
        for (User user : users.values()) {
            sum += user.getDebt();
        }

        return "" + sum;

    }

    // !-------------------------------------------------------------------------------------------REPORT_MOST_POPULAR
    /**
     * Generates a report of the most popular books for the specified library.
     *
     * @param libraryId The ID of the library for which the most popular books
     *                  report is generated.
     * @return A string representing the most popular books report. If the library
     *         with the specified ID is not found, returns "not-found".
     */
    public String reportMostPopular(String libraryId) {

        Library targrtLibrary = libraries.get(libraryId);
        if (targrtLibrary == null) {
            return "not-found";
        }

        return targrtLibrary.reportMostPopular();

    }

    // !-------------------------------------------------------------------------------------------REPORT_SELL
    /**
     * Generates a report of book sales for the specified library.
     *
     * @param libraryId The ID of the library for which the sales report is
     *                  generated.
     * @return A string representing the sales report. If the library with the
     *         specified ID is not found, returns "not-found".
     */
    public String reportSell(String libraryId) {

        Library targrtLibrary = libraries.get(libraryId);
        if (targrtLibrary == null) {
            return "not-found";
        }

        return targrtLibrary.reportSell();
    }
    // !-------------------------------------------------------------------------------------------RTEST

    public void res() {

        System.out.println(libraries.size() + " libraries");
        System.out.println(categories.size() + " categories");
        System.out.println(users.size() + " users");

        for (Library library : libraries.values()) {

            library.res();

        }

    }

}
