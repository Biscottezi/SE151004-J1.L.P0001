
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Date;
import java.util.Calendar;
import java.util.StringTokenizer;

public class StudentList extends ArrayList<Student>{
    public int searchStdID(String stdID){
        for (int i = 0; i < this.size(); ++i){
            if (stdID.equalsIgnoreCase(this.get(i).getStudentID())) return i;
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
    
    private void validate(String info) throws InvalidInfo{
        if (info.isEmpty())
            throw new InvalidInfo("ID/first name/last name/gender/phone number/date of birth must not null.");
    }
    
    private boolean isLeapYear(int y){
        boolean result = false;
        if ((y % 400 == 0)|| ((y % 4 == 0)&& (y % 100 != 0))) result = true;
        return result;
    }
    
    private boolean isValid(int d, int m, int y){
        if (y < 0 || m < 0 || m > 12 || d < 1 || d > 31) return false;
        int maxD = 31;
        if (m == 4 || m == 6 || m == 9 || m == 11) maxD = 30;
        else
            if (m == 2){
                if (isLeapYear(y)) maxD = 29;
                else maxD = 28;
            }
        return d < maxD;
    }
    
    public long toDate(String dmy){
        StringTokenizer stk = new StringTokenizer(dmy, "/-");
        int d = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        int y = Integer.parseInt(stk.nextToken());
        if (!isValid(d, m, y)) return -1;
        Calendar cal = Calendar.getInstance();
        cal.set(y, m-1, d);
        long t = cal.getTime().getTime();
        return t;
    }
    
    public void addStudent(){
        Scanner sc = new Scanner(System.in);
        boolean valid;
        String stdID, firstName, lastName, gender, email, phone, dob;
        System.out.print("Enter student's ID: ");
        stdID = sc.nextLine().toUpperCase();
        int pos = searchStdID(stdID);
        if (pos > -1) System.out.println("ID duplicated.");
        do {
            valid = true;
            System.out.print("Enter student's first name: ");
            firstName = sc.nextLine().toLowerCase().trim();
            try {
                validate(firstName);
            }
            catch(InvalidInfo e) {
                System.out.println(e);
                valid = false;
            }
        }
        while(valid == false);
        firstName = formatName(firstName);
        do {
            valid = true;
            System.out.print("Enter student's last name: ");
            lastName = sc.nextLine().toLowerCase().trim();
            try {
                validate(lastName);
            }
            catch(InvalidInfo e){
                System.out.println(e);
                valid = false;
            }
        }
        while(valid == false);
        lastName = formatName(lastName);
        do {
            valid = true;
            System.out.print("Enter student's gender: ");
            gender = sc.nextLine().toUpperCase();
            try {
                validate(gender);
            }
            catch(InvalidInfo e){
                System.out.println(e);
                valid = false;
            }
        }
        while(valid == false);
        do {
            valid = true;
            System.out.print("Enter student's email: ");
            email = sc.nextLine();
            try {
                validate(email);
            }
            catch(InvalidInfo e){
                System.out.println(e);
                valid = false;
            }
        }
        while(valid == false);
        do {
            valid = true;
            System.out.print("Enter student's phone number: ");
            phone = sc.nextLine();
            try {
                validate(phone);
            }
            catch(InvalidInfo e){
                System.out.println(e);
                valid = false;
            }
            if (valid == true)
                if (phone.length() < 10 || phone.length() > 12) {
                    System.out.println("Phone number must be between 10 and 12 digits.");
                    valid = false;
                }
        }
        while(valid == false);
        do {
            valid = true;
            System.out.print("Enter student's date of birth (dd/mm/yyyy): ");
            dob = sc.nextLine();
            try{
                validate(dob);
            }
            catch(InvalidInfo e){
                System.out.println(e);
                valid = false;
            }
        }
        while(valid == false);
        Date dateOfBirth = new Date(toDate(dob));
        Student st = new Student(stdID, firstName, lastName, gender, email, phone, dateOfBirth);
        this.add(st);
        System.out.println("New sutdent succesfully added.");
    }
    
    public void updateStudentMenu(){
        Scanner sc = new Scanner(System.in);
        String stdId;
        System.out.print("Enter a student ID to update:");
        stdId = sc.nextLine();
        int pos = searchStdID(stdId);
        if (pos == -1){
            System.out.println("Student does not exist.");
            return;
        }
        System.out.print("1. Update student information\n2. Delete student\nOthers. Back to menu\nSelect an option:");
        int userChoice = Integer.parseInt(sc.nextLine());
        switch (userChoice){
            case 1:
                updateStudent(pos);
                break;
            case 2:
                deleteStudent(pos);
                break;
            default:
                break;
        }
    }
    
    public void updateStudent(int pos){
        System.out.println("If there's any information you do not wish to change, please leave it blank.");
        Scanner sc = new Scanner(System.in);
        String firstName, lastName, gender, email, phone;
        System.out.print("Enter new first name: ");
        firstName = sc.nextLine().trim();
        if (!(firstName.isEmpty())) this.get(pos).setFirstName(firstName);
        System.out.print("Enter new last name: ");
        lastName = sc.nextLine().trim();
        if (!(lastName.isEmpty())) this.get(pos).setLastName(lastName);
        System.out.print("Enter new gender: ");
        gender = sc.nextLine();
        if (!(gender.isEmpty())) this.get(pos).setGender(gender);
        System.out.print("Enter new email: ");
        email = sc.nextLine();
        if (!(email.isEmpty())) this.get(pos).setEmail(email);
        System.out.print("Enter new phone number: ");
        phone = sc.nextLine();
        if (!(phone.isEmpty())) {
            if (phone.length() < 10 || phone.length() > 12)
                System.out.println("Phone number must be between 10 and 12 digits");
            else 
                this.get(pos).setPhone(phone);
        }
        System.out.println("Update successful.");
    }
    
    private void deleteStudent(int pos){
        if (this.get(pos).canDelete == false){
            System.out.println("You cannot delete this student.");
        }
        else {
            Scanner sc = new Scanner(System.in);
            System.out.print("Are you sure you want to delete this student?(Y/N):");
            String answer = sc.nextLine().toLowerCase();
            if (answer.startsWith("y")) {
                this.remove(pos);
                System.out.println("Student removed succesfully.");
            }
            else{
                System.out.println("Student is not removed.");
            }
        }
    }
}

class InvalidInfo extends Exception{
    InvalidInfo(String info){
        super(info);
    }
}
