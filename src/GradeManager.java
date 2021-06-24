import java.util.Scanner;

public class GradeManager {
    public static void main(String[] args) {
        System.out.println("Hello");
       int userChoice;
       String answer;
       Scanner sc = new Scanner(System.in);
       Menu menu = new Menu();
       StudentList stdList = new StudentList();
       SubjectList sbjList = new SubjectList();
       GradeList grdList = new GradeList(stdList, sbjList);
       menu.add("Add new student");
       menu.add("Update student");
       menu.add("Add new subject");
       menu.add("Update subject");
       menu.add("Enter grade");
       menu.add("Display student grade report");
       menu.add("Display subject grade report");
       menu.add("Quit");
       do {
            userChoice = menu.getUserChoice();
            switch (userChoice){
            case 1:
                do{
                    stdList.addStudent();
                    System.out.print("Do you want to go back to menu?(Y/N): ");
                    answer = sc.nextLine().toLowerCase();
                }
                while(answer.startsWith("n"));
                break;
            case 2:
                do {
                    stdList.updateStudentMenu();
                    System.out.print("Do you want to go back to update menu?(Y/N): ");
                    answer = sc.nextLine().toLowerCase();
                }
                while(answer.startsWith("y"));
                break;
            case 3:
                do{
                    sbjList.addSubject();
                    System.out.print("Do you want to go back to menu?(Y/N): ");
                    answer = sc.nextLine().toLowerCase();
                }
                while(answer.startsWith("n"));
                break;
            case 4:
                do {
                    sbjList.updateSubjectMenu();
                    System.out.print("Do you want to go back to update menu?(Y/N): ");
                    answer = sc.nextLine().toLowerCase();
                }
                while(answer.startsWith("y"));
                break;
            case 5:
                do{
                    grdList.addGrade();
                    System.out.print("Do you want to go back to menu?(Y/N): ");
                    answer = sc.nextLine().toLowerCase();
                }
                while(answer.startsWith("n"));
                break;
            case 6:
                do{
                    grdList.displayStudentGrade();
                    System.out.print("Do you want to go back to menu?(Y/N): ");
                    answer = sc.nextLine().toLowerCase();
                }
                while(answer.startsWith("n"));
                break;
            case 7:
                do{
                    grdList.displaySubjectGrade();
                    System.out.print("Do you want to go back to menu?(Y/N): ");
                    answer = sc.nextLine().toLowerCase();
                }
                while(answer.startsWith("n"));
                break;
           }
       }
       while (userChoice > 0 && userChoice < 8);
    }
}
