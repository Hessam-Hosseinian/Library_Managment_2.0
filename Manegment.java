import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        if (targetLibrary.getDocuments(docId) == null) {
            return "not-found";

        }

        if (targetLibrary.checkBorrowed(docId)) {

            return "not-allowed";
        }

        targetLibrary.removeResource(docId);

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
        int debt = library.returning(borrow, document, user);
        if (debt < 0) {
            return "not-found";
        }
        if (debt > 0) {
            return "" + debt;
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
    public String addComment(String userId, String pass, String libraryId, String documentId, String strComment) {

        User targetUser = users.get(userId);
        if (targetUser == null) {
            return "not-found";
        }
        if (!targetUser.getPassword().equals(pass)) {
            return "invalid-pass";

        }
        if (targetUser instanceof Staff) {
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

    // !-------------------------------------------------------------------------------------------
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

    // !-------------------------------------------------------------------------------------------
    public StringBuilder searchUser(String userId, String pass, String key) {
        User user = users.get(userId);

        if (user == null) {
            return new StringBuilder("not-found");

        }
        if (!user.getPassword().equals(pass)) {
            return new StringBuilder("invalid-pass");

        }
        if (!(user instanceof Professor || user instanceof Staff)) {

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

    // !-------------------------------------------------------------------------------------------

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
        Category targeCategory = categories.get(categoryId);

        int[] count = targetLibrary.categoryReport(targeCategory);
        System.out.println(count[0] + " " + count[1] + " " + count[2] + " " + count[3]);

    }

    public static Category getCategory(String categoryId) {

        return categories.get(categoryId);
    }

    // !-------------------------------------------------------------------------------------------

    public String libraryReport(String libraryId) {

        Library targetLibrary = libraries.get(libraryId);
        if (targetLibrary == null) {

            return "not-found";

        }
        return targetLibrary.libraryReport();

    }
    // !-------------------------------------------------------------------------------------------

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
    // !-------------------------------------------------------------------------------------------

    public String reportPenaltiesSum(String managerID, String managetPass) {

        User targetUser = users.get(managerID);
        if (targetUser == null) {
            return "not-found";

        }
        if (!(targetUser.getPassword().equals(managetPass))) {
            return "invalid-pass";

        }
        int sum = 0;
        for (User user : users.values()) {
            sum += user.getDebt();
        }

        return "" + sum;

    }

    public void res() {

        System.out.println(libraries.size() + " libraries");
        System.out.println(categories.size() + " categories");
        System.out.println(users.size() + " users");

        for (Library library : libraries.values()) {

            library.res();

        }

    }

}
