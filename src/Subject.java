
public class Subject {
    private int credit;
    private String subjectID, subjectName;
    protected boolean canDelete = true;

    public Subject() {
    }

    public Subject(int credit, String subjectID, String subjectName) {
        this.credit = credit;
        this.subjectID = subjectID;
        this.subjectName = subjectName;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
