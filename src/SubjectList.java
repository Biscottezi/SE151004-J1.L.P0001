import java.util.ArrayList;
import java.util.Scanner;

public class SubjectList extends ArrayList<Subject>{
    public int searchSubjectID(String subjectID){
        for (int i = 0; i < this.size(); ++i) {
            if (this.get(i).getSubjectID().equals(subjectID)) {
                return i;
            }
        }
        return -1;
    }
    
    private static String formatName(String name){
        name = name.replaceAll("\\s+", " ");
        char[] chars = name.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        for (int i = 1; i < chars.length; ++i){
            if (Character.isWhitespace(chars[i]))
                chars[i + 1] = Character.toUpperCase(chars[i + 1]);
        }
        return String.valueOf(chars);
    }
    
    public void addSubject(){
        Scanner sc = new Scanner(System.in);
        String subjectID, subjectName;
        int credit;
        System.out.print("Enter subject ID: ");
        subjectID = sc.nextLine().toUpperCase();
        int pos = searchSubjectID(subjectID);
        if (pos > -1) {
            System.out.println("Subject ID duplicated.");
            return;
        }
        do {
            System.out.print("Enter subject name: ");
            subjectName = formatName(sc.nextLine().toLowerCase().trim());
            if (subjectName.isEmpty())
                System.out.println("Subject name must not null.");
        }
        while(subjectName.isEmpty());
        do {
            System.out.print("Enter subject's credit: ");
            credit = Integer.parseInt(sc.nextLine());
            if (credit < 1)
                System.out.println("Credit must be a positive number.");
        }
        while(credit < 1);
        Subject sbj = new Subject(credit, subjectID, subjectName);
        this.add(sbj);
        System.out.println("New subject succesfully added.");
    }
    
    public void updateSubjectMenu(){
        Scanner sc = new Scanner(System.in);
        String subjectID;
        System.out.print("Enter a subject ID to update: ");
        subjectID = sc.nextLine().toUpperCase();
        int pos = searchSubjectID(subjectID);
        if (pos == -1) {
            System.out.println("Subject does not exist.");
            return;
        }
        System.out.println("1. Update subject information");
        System.out.println("2. Delete subject information");
        System.out.println("Others. Back to menu");
        System.out.print("Select an option: ");
        int userChoice = Integer.parseInt(sc.nextLine());
        switch (userChoice) {
            case 1:
                updateSubject(pos);
                break;
            case 2:
                deleteSubject(pos);
                break;
            default:
                break;
        }
    }
    
    private void updateSubject(int pos){
        Scanner sc = new Scanner(System.in);
        String subjectName, tmp;
        int credit = 1;
        System.out.println("If there's any information you do not wish to change, please leave it blanked.");
        System.out.print("Enter new subject name: ");
        subjectName = sc.nextLine();
        if (!(subjectName.isEmpty()))
            this.get(pos).setSubjectName(subjectName);
        do {
            System.out.print("Enter new number of credits: ");
            tmp = sc.nextLine();
            if (!(tmp.isEmpty())) {
                credit = Integer.parseInt(tmp);
                if (credit < 1)
                    System.out.println("Number of credits must be a positive integer.");
                else this.get(pos).setCredit(credit);
            }
        }
        while(credit < 1);
    }
    
    private void deleteSubject(int pos){
        if (this.get(pos).canDelete == false){
            System.out.println("You cannot delete this subject.");
        }
        else {
            Scanner sc = new Scanner(System.in);
            System.out.print("Are you sure you want to delete this subject?(Y/N):");
            String answer = sc.nextLine().toLowerCase();
            if (answer.startsWith("y")) {
                this.remove(pos);
                System.out.println("Subject removed succesfully.");
            }
            else{
                System.out.println("Subject is not removed.");
            }
        }
    }
}
