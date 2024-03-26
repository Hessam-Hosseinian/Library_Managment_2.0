import user.Professor;
import user.Staff;
import user.Student;
import user.Manager;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import doc.Book;
import doc.BuyableBook;
import doc.Thesis;
import doc.TreasureBook;

public class CommandManeger {

    Manegment manegment = new Manegment();

    public void input(String input) throws ParseException {

        String[] command = input.split("[#|]");

        if (command[0].equals("add-library")) {

            if (manegment.checkAdminPermission(command[1], command[2])) {
                addLibrary(command[3], command[4], command[5], Integer.valueOf(command[6]),
                        command[7]);
            }

        }

        else if (command[0].equals("add-category")) {

            if (manegment.checkAdminPermission(command[1], command[2])) {

                addCategory(command[3], command[4], command[5]);

            }

        }

        else if (command[0].equals("add-student")) {

            if (manegment.checkAdminPermission(command[1], command[2])) {

                addStudent(command[3], command[4], command[5], command[6], command[7], command[8], command[9]);

            }

        }

        else if (command[0].equals("add-staff")) {

            if (manegment.checkAdminPermission(command[1], command[2])) {
                addStaff(command[3], command[4], command[5], command[6], command[7], command[8], command[9],
                        command[10]);

            }
        }

        else if (command[0].equals("add-manager")) {

            if (manegment.checkAdminPermission(command[1], command[2])) {
                addManager(command[3], command[4], command[5], command[6], command[7], command[8], command[9],
                        command[10]);
            }

        }

        else if (command[0].equals("remove-user")) {

            if (manegment.checkAdminPermission(command[1], command[2])) {

                removeUser(command[3]);
            }

        }

        else if (command[0].equals("add-book")) {

            if (manegment.checkManagerPermission(command[1], command[2], command[10])) {

                addBook(command[3], command[4], command[5], command[6], command[7], Integer.valueOf(command[8]),
                        command[9], command[10]);
            }

        }

        else if (command[0].equals("add-thesis")) {

            if (manegment.checkManagerPermission(command[1], command[2], command[9])) {

                addThesis(command[3], command[4], command[5], command[6], command[7], command[8], command[9]);

            }

        }

        else if (command[0].equals("add-ganjineh-book")) {

            if (manegment.checkManagerPermission(command[1], command[2], command[10])) {

                addTreasureBook(command[3], command[4], command[5], command[6], command[7],
                        command[8], command[9],
                        command[10]);

            }

        }

        else if (command[0].equals("add-selling-book")) {

            if (manegment.checkManagerPermission(command[1], command[2], command[12])) {
                addSellingBook(command[3], command[4], command[5], command[6], command[7], Integer.valueOf(command[8]),
                        Integer.valueOf(command[9]), Integer.valueOf(command[10]), command[11], command[12]);

            }

        }

        else if (command[0].equals("remove-resource")) {

            if (manegment.checkManagerPermission(command[1], command[2], command[4])) {

                removeResource(command[3], command[4]);

            }

        }

        else if (command[0].equals("borrow")) {

            borrow(command[1], command[2], command[3], command[4], command[5], command[6]);

        }

        else if (input.contains("return")) {
            returning(command[1], command[2], command[3], command[4], command[5], command[6]);
        }

        else if (input.contains("buy")) {
            buy(command[1], command[2], command[3], command[4]);
        }

        else if (input.contains("read")) {
            read(command[1], command[2], command[3], command[4], command[5], command[6]);
        }

        else if (input.contains("add-comment")) {
            addComment(command[1], command[2], command[3], command[4], command[5]);
        }

        else if (input.contains("search-user")) {
            searchUser(command[1], command[2], command[3]);
        }

        else if (input.contains("search")) {
            search(command[1]);
        }

        else if (input.contains("category-report")) {

            if (manegment.checkManagerPermission(command[1], command[2], command[4])) {

                System.out.println(2 / 0);
            }
        }

    }

    // !--------------------------------------------------------------------------------------------------

    public void addLibrary(String libraryId, String libraryName, String foundationYear,
            int deskNumber,
            String address) {

        Library library = new Library(libraryId, libraryName, foundationYear,
                deskNumber, address);

        System.out.println(manegment.addLibrary(library));

    }
    // ?---------------------------------------------------------------------

    public void addCategory(String categoryId, String categoryName, String subCategory) {

        Category category = new Category(categoryId, categoryName, subCategory);

        System.out.println(manegment.addCategory(category));
    }
    // ?---------------------------------------------------------------------

    public void addStudent(String studentId, String password, String firstName,
            String lastName, String codeId, String birthday, String address) {

        Student student = new Student(studentId, password, firstName, lastName, codeId,
                birthday, address);

        System.out.println(manegment.addStudent(student));
    }
    // ?---------------------------------------------------------------------

    public void addStaff(String StaffId, String password, String firstName, String lastName, String codeId,
            String birthday,
            String address, String or) {

        if (or.equals("staff")) {

            Staff staff = new Staff(StaffId, password, firstName, lastName, codeId, birthday,
                    address);

            System.out.println(manegment.addStaff(staff));

        } else if (or.equals("professor")) {
            Professor professor = new Professor(StaffId, password, firstName, lastName, codeId, birthday, address);
            System.out.println(manegment.addStaff(professor));
        }
    }

    // ?---------------------------------------------------------------------

    public void addManager(String managerId, String password, String firstName,
            String lastName, String codeId, String birthday, String address, String libraryId) {

        Manager manager = new Manager(managerId, password, firstName, lastName, codeId,
                birthday, address, libraryId);

        System.out.println(manegment.addManager(manager));
    }
    // ?---------------------------------------------------------------------

    public void removeUser(String userId) {

        System.out.println(manegment.removeUser(userId));

    }

    // ?---------------------------------------------------------------------
    public void addBook(String id, String name, String authorName, String publisher, String year, int numBook,
            String categoryId, String libraryId) {

        Book book = new Book(id, name, authorName, publisher, year, numBook,
                categoryId, libraryId);

        System.out.println(manegment.addBook(book));
    }

    // ?---------------------------------------------------------------------
    public void addThesis(String id, String name, String studentName, String professor, String year, String categoryId,
            String libraryId) {
        Thesis thesis = new Thesis(id, name, studentName, professor, year, categoryId,
                libraryId);
        System.out.println(manegment.addThesis(thesis));

    }
    // ?---------------------------------------------------------------------

    public void addTreasureBook(String id, String name, String authorName, String publisher, String year,
            String donor, String categoryId, String libraryId) {

        TreasureBook treasureBook = new TreasureBook(id, name, authorName, publisher, year,
                donor, categoryId, libraryId);

        System.out.println(manegment.addTreasureBook(treasureBook));
    }
    // ?---------------------------------------------------------------------

    public void addSellingBook(String id, String name, String authorName, String publisher, String year, int numBook,
            int price, int discountAmount, String categoryId, String libraryId) {

        BuyableBook buyableBook = new BuyableBook(id, name, authorName, publisher, year, numBook,
                price, discountAmount, categoryId, libraryId);

        System.out.println(manegment.addSellingBook(buyableBook));
    }

    // ?---------------------------------------------------------------------

    public void removeResource(String id, String libraryId) {

        System.out.println(manegment.removeResource(id, libraryId));
    }
    // ?---------------------------------------------------------------------

    public void borrow(String userId, String password, String libraryId, String docId, String strDate, String hour)
            throws ParseException {

        java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(strDate + " " + hour);

        Date date = new Date(utilDate.getTime());

        Borrow borrow = new Borrow(date, userId, docId, libraryId);

        System.out.println(manegment.borrow(borrow, password));

    }
    // ?---------------------------------------------------------------------

    public void returning(String userId, String pass, String libraryId, String docId, String strDate, String hour)
            throws ParseException {
        java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(strDate + " " + hour);

        Date date = new Date(utilDate.getTime());
        Borrow borrow = new Borrow(date, userId, docId, libraryId);
        System.out.println(manegment.returning(borrow, pass));
    }
    // ?---------------------------------------------------------------------

    public void buy(String userId, String pass, String libraryId, String documentId) {
        manegment.buy(userId, pass, libraryId, documentId);

    }

    // ?---------------------------------------------------------------------
    public void read(String userId, String pass, String libraryId, String documentId, String strDate, String hour)
            throws ParseException {
        java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(strDate + " " + hour);

        Date date = new Date(utilDate.getTime());

        Read read = new Read(userId, libraryId, documentId, date);
        manegment.read(read, pass);

    }

    // ?---------------------------------------------------------------------

    public void addComment(String userId, String pass, String libraryId, String documentId, String strComment) {

        System.out.println(manegment.addComment(userId, pass, libraryId, documentId, strComment));

    }
    // ?---------------------------------------------------------------------

    public void search(String key) {
        System.out.println(manegment.search(key));
    }

    // ?---------------------------------------------------------------------
    public void searchUser(String userId, String pass, String key) {

        System.out.println(manegment.searchUser(userId, pass, key));
    }

    // ?---------------------------------------------------------------------
    // public void categoryReport(String categoryId, String librayId) {
    // System.out.println(manegment.categoryReport(categoryId, librayId));
    // }
    // ?---------------------------------------------------------------------

    public void res() {
        manegment.res();

    }
}
