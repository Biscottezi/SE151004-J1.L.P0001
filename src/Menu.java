import java.util.ArrayList;
import java.util.Scanner;

public class Menu extends ArrayList<String>{
    public Menu(){
        super();
    }
    
    public int getUserChoice(){
        Scanner sc = new Scanner(System.in);
        int choice;
        for (int i = 0; i < this.size(); ++i){
            System.out.println((i + 1) + "_" + this.get(i));
        }
        System.out.print("Select an  option: ");
        choice = Integer.parseInt(sc.nextLine());
        return choice;
    }
}