import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Biudzetas b1;
    public static void main(String[] args) {
        b1 = new Biudzetas();
        b1.fillData();
        Scanner scanner = new Scanner(System.in);
        String userChoice = "";
        while(!userChoice.equals("0")){
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
                    printIncomeStatement();
                    break;
                case "4":
                    printOutgoingStatement();
                    break;
                case "5":
                    System.out.println("Balansas");
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Įvestas neatpažintas simbolis");;
                    break;
            }
        }
        scanner.close();
    }

    private static void printOutgoingStatement() {
        printTableTop();
        ArrayList<OutgoingStatement> outgoingStatements = getOutgoingStatements();
        for(int i = 0; i < outgoingStatements.size(); i++){
            printDataLine(outgoingStatements.get(i).getId(), outgoingStatements.get(i).getProcessDate(), outgoingStatements.get(i).getCategory(), outgoingStatements.get(i).isPaymentMethod(), outgoingStatements.get(i).getAmount(), outgoingStatements.get(i).getAdditionalInfo());
        }
        printVerticalLine();
    }

    private static void printIncomeStatement() {
        printTableTop();
        ArrayList<IncomeStatement> incomeStatements = getIncomeStatement();
        for(int i = 0; i < incomeStatements.size(); i++){
            printDataLine(incomeStatements.get(i).getId(), incomeStatements.get(i).getProcessDate(), incomeStatements.get(i).getCategory(), incomeStatements.get(i).isTransferedToTheBank(), incomeStatements.get(i).getAmount(), incomeStatements.get(i).getAdditionalInfo());
        }
        printVerticalLine();
    }

    private static void printTableTop() {
        printVerticalLine();
        System.out.println(String.format("| %5s |\t%-20s |\t%-20s |\t%-20s |\t%10s |\t%-30s |", "Nr", "Data", "Kategorija", "Kortele/pavedimu", "Suma", "Komentaras").toString());
        printVerticalLine();
    }

    private static void printDataLine(int id, LocalDateTime processDate, int category, boolean throughBank, double amount, String additionalInfo){
        System.out.println(String.format("| %5d |\t%-20s |\t%-20s |\t%-20s |\t%10.2f |\t%-30s |", id, processDate, getCategoryName(category), booleanInLT(throughBank), amount, additionalInfo).toString());
    }

    private static String getCategoryName(int category){
        String name = "";
        switch(category){
            case 1:
                name = Categories.FOOD.getCategorie();
                break;
            case 2:
                name = Categories.TRANSPORT.getCategorie();
                break;
            case 3:
                name = Categories.CLOTHING.getCategorie();
                break;
            case 4:
                name = Categories.ENTERTAIMENT.getCategorie();
                break;
            case 5:
                name = Categories.HOUSEHOLD.getCategorie();
                break;
            case 6:
                name = Categories.HEALTH.getCategorie();
                break;
            case 7:
                name = Categories.SALES.getCategorie();
                break;
            case 8:
                name = Categories.MAIN_JOB.getCategorie();
                break;
            case 9:
                name = Categories.EXTRA_JOB.getCategorie();
                break;
            case 0:
                name = Categories.OTHER.getCategorie();
                break;
        }
        return name;
    }

    private static String booleanInLT(boolean trueFalse){
        if(trueFalse){
            return "TAIP";
        }
        else{
            return "NE";
        }

    }

    private static void printVerticalLine(){
        System.out.println(String.format("%-"+131+"s", "|").replaceAll("\\s(?=\\s+$|$)", "-")+"|"); //pasinagrineti, kaip veikia
    }

    private static void printMainMenu(){
        System.out.println("Pasirinkite norimą operaciją:");
        System.out.println("1. Įvesti pajamas");
        System.out.println("2. Įvesti išlaidas");
        System.out.println("3. Gauti pajamų irašą");
        System.out.println("4. Gauti išlaidų irašą");
        System.out.println("5. Balansas");
        System.out.println("0. Baigti darbą");
    }

    private static ArrayList<IncomeStatement> getIncomeStatement(){
        return b1.getIncomeStatements();
    }

    private static ArrayList<OutgoingStatement> getOutgoingStatements(){
        return b1.getOutgoingStatements();
    }

}