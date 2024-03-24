import java.text.ParseException;
import java.text.SimpleDateFormat;
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

public class Manegment {

    private HashMap<String, Library> libraries;

    private HashMap<String, User> users;

    private HashMap<String, Category> categories;

    public Manegment() {

        this.libraries = new HashMap<>();
        this.users = new HashMap<>();
        this.categories = new HashMap<>();

        Admin admin = new Admin("admin", "AdminPass", "-", "-", "-", "-", "-");
        users.put("admin", admin);

        Category nullCategory = new Category("null", "null", "null");
        categories.put("null", nullCategory);

    }
    // !-------------------------------------------------------------------------------------------

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

    // !-------------------------------------------------------------------------------------------

    public String addLibrary(Library library) {

        if (libraries.get(library.getLibraryId()) != null) {
            return "duplicate-id";

        }

        libraries.put(library.getLibraryId(), library);
        return "success";

    }
    // !-------------------------------------------------------------------------------------------

    public String addCategory(Category category) {
        if (categories.get(category.getCategoryId()) != null) {
            return "duplicate-id";

        }
        if (categories.get(category.getSubCategory()) == null) {
            return "not-found";

        }

        categories.put(category.getCategoryId(), category);
        return "success";

    }
    // !-------------------------------------------------------------------------------------------

    public String addStudent(Student student) {

        if (users.get(student.getUserId()) != null) {
            return "duplicate-id";

        }
        users.put(student.getUserId(), student);
        return "success";

    }
    // !-------------------------------------------------------------------------------------------

    public String addStaff(Staff staff) {

        if (users.get(staff.getUserId()) != null) {
            return "duplicate-id";

        }
        users.put(staff.getUserId(), staff);
        return "success";

    }

    public String addStaff(Professor professor) {
        if (users.get(professor.getUserId()) != null) {
            return "duplicate-id";

        }
        users.put(professor.getUserId(), professor);
        return "success";

    }
    // !-------------------------------------------------------------------------------------------

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

    // !-------------------------------------------------------------------------------------------

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
    // !-------------------------------------------------------------------------------------------

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
    // !-------------------------------------------------------------------------------------------

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
    // !-------------------------------------------------------------------------------------------

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
    // !-------------------------------------------------------------------------------------------

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
    // !-------------------------------------------------------------------------------------------

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
    // !-------------------------------------------------------------------------------------------

    public String removeResource(String docId, String libraryId) {

        Library targetLibrary = libraries.get(libraryId);
        if (targetLibrary == null) {
            return "not-found";

        }
        if (!targetLibrary.removeResource(docId)) {
            return "not-found";
        }
        if (targetLibrary.checkBorrowed(docId)) {

            return "not-allowed";
        }
        // System.out.println(targetLibrary.checkBorrowed(docId));

        return "success";
    }

    // !-------------------------------------------------------------------------------------------
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

    public int countBorrow(String userId) {
        int borrowed = 0;
        for (Library library : libraries.values()) {
            borrowed += library.countBorrows(userId);
        }
        return borrowed;
    }

    private boolean checkDelay(Borrow borrow, Document document, User user) {
        for (Library library : libraries.values()) {
            if (library.hasDelay(borrow, document, user, borrow.getUserId())) {
                return true;
            }
        }
        return false;
    }

    // !-------------------------------------------------------------------------------------------
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
        int hold = library.returning(borrow, document, user);
        if (hold < 0) {
            return "not-found";
        }
        if (hold > 0) {
            return "" + hold;
        }
        return "success";
    }
    // !-------------------------------------------------------------------------------------------

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
        if ((targetUser instanceof Manager)) {
            System.out.println("permission-denied");
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
        if (targetUser.getDebt() != 0) {
            System.out.println("not-allowed");
            return;
        }

        if (targetLibrary.buyBook(documentId)) {
            System.out.println("success");
            return;
        }
        return;

    }
    // !-------------------------------------------------------------------------------------------

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
    // !-------------------------------------------------------------------------------------------

    public void res() {

        System.out.println(libraries.size() + " libraries");
        System.out.println(categories.size() + " categories");
        System.out.println(users.size() + " users");

        for (Library library : libraries.values()) {

            library.res();

        }

    }

}
