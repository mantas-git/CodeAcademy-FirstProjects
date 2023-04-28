import java.util.Scanner;

public class Main {
    static Biudzetas b1;
    public static void main(String[] args) {
        b1 = new Biudzetas();
        Scanner scanner = new Scanner(System.in);
        String userChoice = "";
        while(!userChoice.equals("5")){
            System.out.println("Pasirinkite norimą operaciją:");
            System.out.println("1. Įvesti pajamas");
            System.out.println("2. Įvesti išlaidas");
            System.out.println("3. Gauti pajamų irašą");
            System.out.println("4. Gauti išlaidų irašą");
            System.out.println("5. Baigti darbą");

            userChoice = scanner.next();

            switch (userChoice){
                case "1":
                    b1.pridetiPajamuIrasa();
                    break;
                case "2":
                    b1.pridetiIslaiduIrasa();
                    break;
                case "3":
                    IncomeStatement[] incomeStatements = getIncomeStatement();
                    for (IncomeStatement stringInList : incomeStatements) {
                            System.out.println(stringInList);
                    }
                   // for(IncomeStatement[] elements: )
                   // b1.gautiPajamuIrasa();
                    break;
                case "4":
                    OutgoingStatement[] outgoingStatements = getOutgoingStatements();
                    for (OutgoingStatement stringInList : outgoingStatements) {
                        System.out.println(stringInList);
                    }
                    //b1.gautiIslaiduIrasa();
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

    private static IncomeStatement[] getIncomeStatement(){
        return b1.getIncomeStatements();

    }

    private static OutgoingStatement[] getOutgoingStatements(){
        return b1.getOutgoingStatements();
    }

}