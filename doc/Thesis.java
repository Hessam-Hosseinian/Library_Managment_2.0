package doc;

public class Thesis extends Document {

    public Thesis(String docId, String title, String studentName, String professorName, String publicationYear,
            String categoryId, String libraryId) {
        super(docId, title, "-", "-", publicationYear, 1, categoryId, libraryId);
        this.studentName = studentName;
        this.professorName = professorName;

    }

    private String studentName;
    private String professorName;

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getProfessorName() {
        return this.professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

}
