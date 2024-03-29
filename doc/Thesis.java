package doc;

/**
 * The Thesis class represents a thesis document in a library system.
 * It extends the Document class and inherits its properties and methods.
 * 
 * @author Hessam Hosseinan
 */
public class Thesis extends Document {

    private String studentName;
    private String professorName;

    /**
     * Constructs a new Thesis object with the specified attributes.
     *
     * @param docId           The unique identifier of the thesis document.
     * @param title           The title of the thesis.
     * @param studentName     The name of the student who authored the thesis.
     * @param professorName   The name of the professor who supervised the thesis.
     * @param publicationYear The publication year of the thesis.
     * @param categoryId      The category ID of the thesis.
     * @param libraryId       The library ID where the thesis is located.
     */
    public Thesis(String docId, String title, String studentName, String professorName, String publicationYear,
            String categoryId, String libraryId) {
        super(docId, title, "-", "-", publicationYear, 1, categoryId, libraryId);
        this.studentName = studentName;
        this.professorName = professorName;

    }

    // !--------------------------------------------STUDENT_NAME
    public String getStudentName() {
        return this.studentName;
    }
    // ?---------------------------------------------

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    // !--------------------------------------------PROFESSOR_NAME

    public String getProfessorName() {
        return this.professorName;
    }
    // ?---------------------------------------------

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

}
