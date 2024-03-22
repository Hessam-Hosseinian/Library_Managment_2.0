import java.util.Date;
import java.util.HashSet;
//?----------------------------------------------------------------------------------------------------------------------

import user.User;

public class Borrow {

    private Date date;

    private String userId;
    private String documentId;
    private String libraryId;
    private boolean isStudent;
    private boolean isProfessor;

    private boolean isBook;

    public Borrow(Date date, String userId, String documentId, String libraryId) {
        this.libraryId = libraryId;
        this.date = date;
        this.userId = userId;
        this.documentId = documentId;
    }
    // ------------------------------------------------------------------------------------------------------------------
    // !------------------------------------------------------------------------------

    // public boolean checkUser(HashSet<String> usersIds) {
    // User targetUser =
    // if (studentIds.contains(userId)) {

    // this.isStudent = true;
    // return true;

    // }

    // else if (staffIds.contains(userId)) {

    // this.isStudent = false;
    // return true;

    // }

    // return false;
    // }

    // !------------------------------------------------------------------------------

    public boolean checkDoc(HashSet<String> bookIds, HashSet<String> thesisIds) {
        if (bookIds.contains(documentId)) {

            this.isBook = true;
            return true;

        }

        else if (thesisIds.contains(documentId)) {

            this.isBook = false;
            return true;
        }

        return false;
    }
    // !------------------------------------------------------------------------------

    public boolean isStudent() {
        return isStudent;
    }
    // !------------------------------------------------------------------------------

    public boolean isBook() {
        return isBook;
    }
    // !------------------------------------------------------------------------------

    // ----------------------------------- Seters and Geters
    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDocumentId() {
        return this.documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getLibraryId() {
        return this.libraryId;
    }

    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
    }

    public boolean isIsStudent() {
        return this.isStudent;
    }

    public boolean getIsStudent() {
        return this.isStudent;
    }

    public void setIsStudent(boolean isStudent) {
        this.isStudent = isStudent;
    }

    public boolean isIsBook() {
        return this.isBook;
    }

    public boolean getIsBook() {
        return this.isBook;
    }

    public void setIsBook(boolean isBook) {
        this.isBook = isBook;
    }

    public boolean isIsProfessor() {
        return this.isProfessor;
    }

    public boolean getIsProfessor() {
        return this.isProfessor;
    }

    public void setIsProfessor(boolean isProfessor) {
        this.isProfessor = isProfessor;
    }
    // ----------------------------------- Seters and Geters

}
// ?----------------------------------------------------------------------------------------------------------------------
