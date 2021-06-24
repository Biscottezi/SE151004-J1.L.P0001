import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class GradeList extends ArrayList<Grade>{
    StudentList stdList;
    SubjectList sbjList;
    
    public GradeList(StudentList stdList, SubjectList sbjList){
        this.stdList = stdList;
        this.sbjList = sbjList;
    }
    
    public int search(String stdID, String sbjID){
        for (int i = 0; i < this.size(); ++i){
            if (this.get(i).getStd().getStudentID().equals(stdID) && this.get(i).getSbj().getSubjectID().equals(sbjID))
                return i;
        }
        return -1;
    }
    
    public int searchStudent(String stdID){
        for (int i = 0; i < stdList.size(); ++i){
            if (stdList.get(i).getStudentID().equals(stdID))
                return i;
        }
        return -1;
    }
    
    public int searchSubject(String sbjID){
        for (int i = 0; i < sbjList.size(); ++i){
            if (sbjList.get(i).getSubjectID().equals(sbjID))
                return i;
        }
        return -1;
    }
    
    public void addGrade(){
        Scanner sc = new Scanner(System.in);
        String stdID, sbjID;
        int stdPos, sbjPos, gradePos;
        double lab, PT, FE;
        do {
            System.out.print("Enter student ID: ");
            stdID = sc.nextLine().toUpperCase();
            stdPos = searchStudent(stdID);
            if (stdPos < 0) System.out.println("Student does not exist.");
        }
        while(stdPos < 0);
        do {
            System.out.print("Enter subject ID: ");
            sbjID = sc.nextLine().toUpperCase();
            sbjPos = searchSubject(sbjID);
            if (sbjPos < 0) System.out.println("Subject does not exist.");
        }
        while(sbjPos < 0);
        gradePos = search(stdID, sbjID);
        if (gradePos > -1){
            System.out.print("This grade exists. Do you want to override?(Y/N): ");
            String answer = sc.nextLine().toLowerCase();
            if (answer.startsWith("n")) return;
        }
        do {
        System.out.print("Enter lab grade: ");
        lab = Double.parseDouble(sc.nextLine());
        if (lab < 0 || lab > 10) System.out.println("All grades must be between 0 and 10.");
        }
        while (lab < 0 || lab > 10);
        do {
        System.out.print("Enter progress test grade: ");
        PT = Double.parseDouble(sc.nextLine());
        if (PT < 0 || PT > 10) System.out.println("All grades must be between 0 and 10.");
        }
        while (PT < 0 || PT > 10);
        do {
        System.out.print("Enter final exam grade: ");
        FE = Double.parseDouble(sc.nextLine());
        if (FE < 0 || FE > 10) System.out.println("All grades must be between 0 and 10.");
        }
        while (FE < 0 || FE > 10);
        Grade grade = new Grade(stdList.get(stdPos), sbjList.get(sbjPos), lab, PT, FE);
        this.add(grade);
        stdList.get(stdPos).canDelete = false;
        sbjList.get(sbjPos).canDelete = false;
        System.out.println("New grade successfully added.");
    }
    
    public void displayStudentGrade(){
        Scanner sc = new Scanner(System.in);
        String stdID;
        System.out.print("Enter a student ID: ");
        stdID = sc.nextLine().toUpperCase();
        int stdPos = stdList.searchStdID(stdID);
        if (stdPos < 0) {
            System.out.println("Student does not exist.");
            return;
        }
        int gradePos = searchStudent(stdID);
        if (gradePos < 0){
            System.out.println("No grade report of this student.");
            return;
        }
        System.out.println("Student ID: " + stdID);
        System.out.println("Student name: " + stdList.get(stdPos).getLastName() + " " + stdList.get(stdPos).getFirstName());
        System.out.println("List of subjects:");
        System.out.println("| ++No++ |++++++++Subject name++++++++ | ++Average mark++ | ++Status++ |");
        int count = 1;
        for (Grade g : this){
            if (stdID.equals(g.getStd().getStudentID())){
                System.out.print("    " + count + "\t");
                System.out.print(g.getSbj().getSubjectName());
                double avg = g.average();
                System.out.print("\t\t" + avg);
                System.out.println("\t\t" + ((avg >= 4) ? "PASS\n" : "NOT PASS\n"));
                ++count;
            }
        }
    }
    
    public void displaySubjectGrade(){
        Scanner sc = new Scanner(System.in);
        String sbjID;
        System.out.print("Enter subject ID: ");
        sbjID = sc.nextLine().toUpperCase();
        int sbjPos = sbjList.searchSubjectID(sbjID);
        if (sbjPos < 0){
            System.out.println("Subject does not exist.");
            return;
        }
        int gradePos = searchSubject(sbjID);
        if (gradePos < 0){
            System.out.println("No grade report of this subject.");
            return;
        }
        System.out.println("Subject ID: " + sbjID);
        System.out.println("Subject name: " + sbjList.get(sbjPos).getSubjectName());
        System.out.println("| ++No++ | ++++++++Student name++++++++ | ++Average mark++ | ++Status++|");
        int count = 1;
        for (Grade g : this){
            if (sbjID.equals(g.getSbj().getSubjectID())){
                System.out.print("    " + count + "\t");
                System.out.print(g.getStd().getLastName() + " " + g.getStd().getFirstName() + "\t\t");
                double avg = g.average();
                System.out.print(avg + "\t");
                System.out.println(avg >= 4 ? "PASS\n" : "NOT PASS\n");
                ++count;
            }
        }
    }
}
    
    