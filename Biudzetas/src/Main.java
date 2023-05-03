import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Biudzetas b1;
    public static void main(String[] args) {
        b1 = new Biudzetas();
        Scanner scanner = new Scanner(System.in);
        String userChoice = "";
        while(!userChoice.equals("5")){
            printMainMenu();
            userChoice = scanner.next();

            switch (userChoice){
                case "1":
                    b1.addIncomeStatement();
                    break;
                case "2":
                    b1.addOutgoingStatement();
                    break;
                case "3":
                    //IncomeStatement[] incomeStatements = getIncomeStatement();
                    ArrayList<IncomeStatement> incomeStatements = getIncomeStatement();
                    for(int i = 0; i < incomeStatements.size(); i++){
                        System.out.println(incomeStatements.get(i));
                    }
                    break;
                case "4":
                    //OutgoingStatement[] outgoingStatements = getOutgoingStatements();
                    ArrayList<OutgoingStatement> outgoingStatements = getOutgoingStatements();
                    for(int i = 0; i < outgoingStatements.size(); i++){
                        System.out.println(outgoingStatements.get(i));
                    }
                    break;
                case "5":
                    break;
                default:
                    System.out.println("Įvestas neatpažintas simbolis");;
                    break;
            }
        }
        scanner.close();
    }

    private static void printMainMenu(){
        System.out.println("Pasirinkite norimą operaciją:");
        System.out.println("1. Įvesti pajamas");
        System.out.println("2. Įvesti išlaidas");
        System.out.println("3. Gauti pajamų irašą");
        System.out.println("4. Gauti išlaidų irašą");
        System.out.println("5. Baigti darbą");
    }

    private static ArrayList<IncomeStatement> getIncomeStatement(){
        return b1.getIncomeStatements();
    }

    private static ArrayList<OutgoingStatement> getOutgoingStatements(){
        return b1.getOutgoingStatements();
    }

}